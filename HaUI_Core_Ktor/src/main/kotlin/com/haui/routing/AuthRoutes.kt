package com.haui.routing

import com.haui.auth.AuthRequest
import com.haui.auth.AuthResponse
import com.haui.auth.JwtConfig
import com.haui.database.DatabaseManager
import com.haui.database.entities.TaiKhoans
import com.haui.database.entities.NguoiDungs
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.ktorm.dsl.*
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toList
import org.mindrot.jbcrypt.BCrypt
import java.util.UUID

fun Route.authRouting() {
    val db = DatabaseManager.database

    route("/api/v1/auth") {

        // DANG NHAP
        post("/login") {
            val request = call.receiveOrNull<AuthRequest>() ?: return@post call.respond(
                HttpStatusCode.BadRequest, AuthResponse("ERROR", message = "Du lieu khong hop le")
            )

            val taiKhoan = withContext(Dispatchers.IO) {
                db.sequenceOf(TaiKhoans).toList().firstOrNull { it.username == request.username }
            }

            if (taiKhoan == null) {
                call.respond(HttpStatusCode.Unauthorized, AuthResponse("ERROR", message = "Tai khoan khong ton tai!"))
                return@post
            }

            val storedPass = taiKhoan.passwordHash
            val isMatch: Boolean = if (storedPass.length > 20 && storedPass[0] == '$') {
                // BCrypt hash (bat dau bang $2a$ hoac $2b$)
                try { BCrypt.checkpw(request.password, storedPass) } catch (e: Exception) { false }
            } else {
                // Plain text
                request.password == storedPass
            }

            if (isMatch) {
                val token = JwtConfig.generateToken(taiKhoan.username, taiKhoan.role)
                call.respond(HttpStatusCode.OK, AuthResponse("SUCCESS", token, taiKhoan.role, taiKhoan.id, "Dang nhap thanh cong!"))
            } else {
                call.respond(HttpStatusCode.Unauthorized, AuthResponse("ERROR", message = "Sai mat khau!"))
            }
        }

        // DANG KY
        post("/register") {
            val request = call.receiveOrNull<AuthRequest>() ?: return@post call.respond(
                HttpStatusCode.BadRequest, AuthResponse("ERROR", message = "Du lieu loi!")
            )

            val existing = withContext(Dispatchers.IO) {
                db.sequenceOf(TaiKhoans).toList().firstOrNull { it.username == request.username }
            }
            if (existing != null) {
                call.respond(HttpStatusCode.Conflict, AuthResponse("ERROR", message = "Ten dang nhap da ton tai!"))
                return@post
            }

            val newId = "TK_" + UUID.randomUUID().toString().replace("-", "").substring(0, 5).uppercase()
            val hashedPw = BCrypt.hashpw(request.password, BCrypt.gensalt(10))

            withContext(Dispatchers.IO) {
                db.insert(TaiKhoans) {
                    set(it.id, newId)
                    set(it.username, request.username)
                    set(it.passwordHash, hashedPw)
                    set(it.role, "USER")
                }
                
                // Tu dong tao Ho so mac dinh cho TaiKhoan moi
                val profileId = "ND_" + UUID.randomUUID().toString().replace("-", "").substring(0, 5).uppercase()
                db.insert(NguoiDungs) {
                    set(it.id, profileId)
                    set(it.ten, request.username)
                    set(it.vaiTro, "USER")
                    set(it.taiKhoanId, newId)
                }
            }
            call.respond(HttpStatusCode.Created, AuthResponse("SUCCESS", null, "USER", newId, "Tao tai khoan thanh cong! ID: $newId"))
        }
    }
}

