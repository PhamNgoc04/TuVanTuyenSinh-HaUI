package com.haui.routing

import com.haui.database.DatabaseManager
import com.haui.database.entities.*
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toList

fun Route.nguoiDungRouting() {
    val db = DatabaseManager.database

    route("/api/v1/nguoi-dung") {

        // Xem ho so nguoi dung theo TaiKhoan id
        get("/{taiKhoanId}") {
            val tkId = call.parameters["taiKhoanId"] ?: ""
            val nd = db.sequenceOf(NguoiDungs).toList().firstOrNull { it.taiKhoanId == tkId }
            if (nd == null) {
                call.respond(HttpStatusCode.NotFound, mapOf("status" to "ERROR", "message" to "Khong tim thay ho so"))
            } else {
                call.respond(mapOf(
                    "id" to nd.id, "ten" to nd.ten, "avatar" to nd.avatar,
                    "email" to nd.email, "vaiTro" to nd.vaiTro
                ))
            }
        }

        // Cap nhat thong tin ho so
        put("/{id}") {
            val id = call.parameters["id"] ?: ""
            val body = call.receive<Map<String, String>>()
            val nd = db.sequenceOf(NguoiDungs).toList().firstOrNull { it.id == id }
            if (nd == null) {
                call.respond(HttpStatusCode.NotFound, mapOf("status" to "ERROR", "message" to "Khong tim thay"))
            } else {
                nd.ten = body["ten"] ?: nd.ten ?: ""
                nd.email = body["email"] ?: nd.email
                nd.flushChanges()
                call.respond(mapOf("status" to "SUCCESS", "message" to "Cap nhat thanh cong"))
            }
        }
    }
}
