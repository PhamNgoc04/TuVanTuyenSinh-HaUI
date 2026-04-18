package com.haui.routing

import com.haui.database.DatabaseManager
import com.haui.database.entities.*
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toList

fun Route.nganhHocRouting() {
    val db = DatabaseManager.database

    route("/api/v1/nganh-hoc") {

        get {
            val list = db.sequenceOf(NganhHocs).toList().map {
                mapOf("maNganh" to it.maNganh, "tenNganh" to it.tenNganh, "moTa" to it.moTa, "coSo" to it.coSo)
            }
            call.respond(mapOf("status" to "SUCCESS", "data" to list))
        }

        get("/{maNganh}") {
            val ma = call.parameters["maNganh"] ?: return@get call.respond(HttpStatusCode.BadRequest)
            val nganh = db.sequenceOf(NganhHocs).toList().firstOrNull { it.maNganh == ma }
            if (nganh == null) call.respond(HttpStatusCode.NotFound, mapOf("status" to "ERROR"))
            else call.respond(mapOf("maNganh" to nganh.maNganh, "tenNganh" to nganh.tenNganh, "moTa" to nganh.moTa, "coSo" to nganh.coSo))
        }

        get("/{maNganh}/nghe-nghiep") {
            val ma = call.parameters["maNganh"] ?: ""
            val list = db.sequenceOf(NganhNghes).toList().filter { it.nganhHocMaNganh == ma }
                .map { mapOf("maNghe" to it.maNghe, "tenNghe" to it.tenNghe, "mucLuong" to it.mucLuong, "tinhTrang" to it.tinhTrang) }
            call.respond(mapOf("status" to "SUCCESS", "data" to list))
        }

        get("/{maNganh}/chi-tieu") {
            val ma = call.parameters["maNganh"] ?: ""
            val list = db.sequenceOf(ChiTieus).toList().filter { it.nganhHocMaNganh == ma }
                .map { mapOf("nam" to it.nam, "soLuong" to it.soLuong, "phuongThuc" to it.phuongThuc) }
            call.respond(mapOf("status" to "SUCCESS", "data" to list))
        }

        get("/{maNganh}/yeu-cau-dau-vao") {
            val ma = call.parameters["maNganh"] ?: ""
            val list = db.sequenceOf(YeuCauDauVaos).toList().filter { it.nganhHocMaNganh == ma }
                .map { mapOf("khoi" to it.khoi, "diemTong" to it.diemTong) }
            call.respond(mapOf("status" to "SUCCESS", "data" to list))
        }

        get("/{maNganh}/tin-chi") {
            val ma = call.parameters["maNganh"] ?: ""
            val list = db.sequenceOf(TinChis).toList().filter { it.nganhHocMaNganh == ma }
                .map { mapOf("id" to it.id, "giaTien" to it.giaTien) }
            call.respond(mapOf("status" to "SUCCESS", "data" to list))
        }
    }
}
