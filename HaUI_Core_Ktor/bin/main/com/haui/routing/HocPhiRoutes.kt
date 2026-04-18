package com.haui.routing

import com.haui.database.DatabaseManager
import com.haui.database.entities.*
import io.ktor.server.application.call
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toList

fun Route.hocPhiRouting() {
    val db = DatabaseManager.database

    route("/api/v1") {

        get("/chuong-trinh-hoc") {
            val list = db.sequenceOf(ChuongTrinhHocs).toList().map {
                mapOf("id" to it.id, "thoiGianHoc" to it.thoiGianHoc, "loai" to it.loai)
            }
            call.respond(mapOf("status" to "SUCCESS", "data" to list))
        }

        get("/hoc-phi") {
            val list = db.sequenceOf(HocPhis).toList().map {
                mapOf("id" to it.id, "soTien" to it.soTien,
                      "namHoc" to it.namHoc, "chuongTrinhHocId" to it.chuongTrinhHocId)
            }
            call.respond(mapOf("status" to "SUCCESS", "data" to list))
        }

        get("/hoc-bong") {
            val list = db.sequenceOf(HocBongs).toList().map {
                mapOf("id" to it.id, "loaiHb" to it.loaiHb,
                      "diemYc" to it.diemYc, "hanhKiemYc" to it.hanhKiemYc)
            }
            call.respond(mapOf("status" to "SUCCESS", "data" to list))
        }

        get("/quy-trinh-nhap-hoc") {
            val list = db.sequenceOf(QuyTrinhNhapHocs).toList().map {
                mapOf("id" to it.id, "noiDung" to it.noiDung)
            }
            call.respond(mapOf("status" to "SUCCESS", "data" to list))
        }
    }
}
