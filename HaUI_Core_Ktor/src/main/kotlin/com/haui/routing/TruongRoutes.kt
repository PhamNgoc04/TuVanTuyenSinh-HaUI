package com.haui.routing

import com.haui.database.DatabaseManager
import com.haui.database.entities.*
import io.ktor.server.application.call
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toList

fun Route.truongRouting() {
    val db = DatabaseManager.database

    route("/api/v1/truong") {

        // Xem thong tin truong HaUI
        get {
            try {
                val list = db.sequenceOf(Truongs).toList().map {
                    mapOf("maTruong" to it.maTruong, "tenTruong" to it.tenTruong,
                          "moTa" to it.moTa, "thanhPho" to it.thanhPho,
                          "quan" to it.quan, "duong" to it.duong)
                }
                call.respond(mapOf("status" to "SUCCESS", "data" to list))
            } catch (e: Exception) {
                call.respond(mapOf("status" to "ERROR", "message" to e.message))
            }
        }

        // Danh sach nganh hoc
        get("/nganh-hoc") {
            try {
                val list = db.sequenceOf(NganhHocs).toList().map {
                    mapOf("maNganh" to it.maNganh, "tenNganh" to it.tenNganh,
                          "moTa" to it.moTa, "coSo" to it.coSo)
                }
                call.respond(mapOf("status" to "SUCCESS", "data" to list))
            } catch (e: Exception) {
                call.respond(mapOf("status" to "ERROR", "message" to e.message))
            }
        }

        // Danh sach tin tuc
        get("/tin-tuc") {
            try {
                val list = db.sequenceOf(TinTucs).toList().map {
                    mapOf("id" to it.id, "tieuDe" to it.tieuDe,
                          "anh" to it.anh, "moTa" to it.moTa, "noiDung" to it.noiDung)
                }
                call.respond(mapOf("status" to "SUCCESS", "data" to list))
            } catch (e: Exception) {
                call.respond(mapOf("status" to "ERROR", "message" to e.message))
            }
        }
    }
}
