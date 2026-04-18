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
import org.ktorm.entity.add
import java.util.UUID

fun Route.adminRouting() {
    val db = DatabaseManager.database

    route("/api/v1/admin") {

        // ===== TRUONG =====
        get("/truong") {
            val list = db.sequenceOf(Truongs).toList().map {
                mapOf("maTruong" to it.maTruong, "tenTruong" to it.tenTruong,
                      "moTa" to it.moTa, "thanhPho" to it.thanhPho,
                      "quan" to it.quan, "duong" to it.duong)
            }
            call.respond(mapOf("status" to "SUCCESS", "data" to list))
        }
        put("/truong/{ma}") {
            val ma = call.parameters["ma"] ?: ""
            val body = call.receive<Map<String, String>>()
            val truong = db.sequenceOf(Truongs).toList().firstOrNull { it.maTruong == ma }
            if (truong != null) {
                truong.tenTruong = body["tenTruong"] ?: truong.tenTruong
                truong.moTa = body["moTa"] ?: truong.moTa
                truong.duong = body["duong"] ?: truong.duong
                truong.flushChanges()
                call.respond(mapOf("status" to "SUCCESS", "message" to "Da cap nhat thong tin truong"))
            } else { call.respond(HttpStatusCode.NotFound, mapOf("status" to "ERROR")) }
        }

        // ===== NGANH HOC =====
        post("/nganh-hoc") {
            val body = call.receive<Map<String, String>>()
            val newNganh = NganhHoc {
                maNganh = body["maNganh"] ?: "NH_"
                tenNganh = body["tenNganh"] ?: "Nganh moi"
                moTa = body["moTa"]
                coSo = body["coSo"]
            }
            db.sequenceOf(NganhHocs).add(newNganh)
            call.respond(HttpStatusCode.Created, mapOf("status" to "SUCCESS", "message" to "Da them nganh hoc"))
        }
        put("/nganh-hoc/{ma}") {
            val ma = call.parameters["ma"] ?: ""
            val body = call.receive<Map<String, String>>()
            val nganh = db.sequenceOf(NganhHocs).toList().firstOrNull { it.maNganh == ma }
            if (nganh != null) {
                nganh.tenNganh = body["tenNganh"] ?: nganh.tenNganh
                nganh.moTa = body["moTa"] ?: nganh.moTa
                nganh.flushChanges()
                call.respond(mapOf("status" to "SUCCESS", "message" to "Da cap nhat nganh"))
            } else { call.respond(HttpStatusCode.NotFound, mapOf("status" to "ERROR")) }
        }
        delete("/nganh-hoc/{ma}") {
            val ma = call.parameters["ma"] ?: ""
            val nganh = db.sequenceOf(NganhHocs).toList().firstOrNull { it.maNganh == ma }
            nganh?.delete()
            call.respond(mapOf("status" to "SUCCESS", "message" to "Da xoa"))
        }

        // ===== NGHE NGHIEP =====
        post("/nghe-nghiep") {
            val body = call.receive<Map<String, String>>()
            val newNghe = NganhNghe {
                maNghe = body["maNghe"] ?: "NN_"
                tenNghe = body["tenNghe"] ?: "Nghe moi"
                mucLuong = body["mucLuong"]
                tinhTrang = body["tinhTrang"]
                nganhHocMaNganh = body["nganhHocMaNganh"]
            }
            db.sequenceOf(NganhNghes).add(newNghe)
            call.respond(HttpStatusCode.Created, mapOf("status" to "SUCCESS", "message" to "Da them nghe nghiep"))
        }
        put("/nghe-nghiep/{ma}") {
            val ma = call.parameters["ma"] ?: ""
            val body = call.receive<Map<String, String>>()
            val nghe = db.sequenceOf(NganhNghes).toList().firstOrNull { it.maNghe == ma }
            if (nghe != null) {
                nghe.tenNghe = body["tenNghe"] ?: nghe.tenNghe
                nghe.mucLuong = body["mucLuong"] ?: nghe.mucLuong
                nghe.tinhTrang = body["tinhTrang"] ?: nghe.tinhTrang
                nghe.flushChanges()
                call.respond(mapOf("status" to "SUCCESS", "message" to "Da cap nhat nghe nghiep"))
            } else { call.respond(HttpStatusCode.NotFound, mapOf("status" to "ERROR")) }
        }
        delete("/nghe-nghiep/{ma}") {
            val ma = call.parameters["ma"] ?: ""
            db.sequenceOf(NganhNghes).toList().firstOrNull { it.maNghe == ma }?.delete()
            call.respond(mapOf("status" to "SUCCESS"))
        }

        // ===== HOC BONG =====
        post("/hoc-bong") {
            val body = call.receive<Map<String, String>>()
            val newHb = HocBong {
                id = body["id"] ?: "HB_"
                loaiHb = body["loaiHb"]
                diemYc = body["diemYc"]
                hanhKiemYc = body["hanhKiemYc"]
                chuongTrinhHocId = body["chuongTrinhHocId"]
            }
            db.sequenceOf(HocBongs).add(newHb)
            call.respond(HttpStatusCode.Created, mapOf("status" to "SUCCESS", "message" to "Da them hoc bong"))
        }
        put("/hoc-bong/{id}") {
            val id = call.parameters["id"] ?: ""
            val body = call.receive<Map<String, String>>()
            val hb = db.sequenceOf(HocBongs).toList().firstOrNull { it.id == id }
            if (hb != null) {
                hb.loaiHb = body["loaiHb"] ?: hb.loaiHb
                hb.diemYc = body["diemYc"] ?: hb.diemYc
                hb.hanhKiemYc = body["hanhKiemYc"] ?: hb.hanhKiemYc
                hb.flushChanges()
                call.respond(mapOf("status" to "SUCCESS", "message" to "Da cap nhat hoc bong"))
            } else { call.respond(HttpStatusCode.NotFound, mapOf("status" to "ERROR")) }
        }
        delete("/hoc-bong/{id}") {
            val id = call.parameters["id"] ?: ""
            db.sequenceOf(HocBongs).toList().firstOrNull { it.id == id }?.delete()
            call.respond(mapOf("status" to "SUCCESS"))
        }

        // ===== TIN TUC =====
        post("/tin-tuc") {
            val body = call.receive<Map<String, String>>()
            val newTin = TinTuc {
                id = body["id"] ?: "TT_"
                tieuDe = body["tieuDe"] ?: "Tin moi"
                anh = body["anh"]
                noiDung = body["noiDung"]
                moTa = body["moTa"]
                truongMaTruong = body["truongMaTruong"]
            }
            db.sequenceOf(TinTucs).add(newTin)
            call.respond(HttpStatusCode.Created, mapOf("status" to "SUCCESS", "message" to "Da them tin tuc"))
        }
        put("/tin-tuc/{id}") {
            val id = call.parameters["id"] ?: ""
            val body = call.receive<Map<String, String>>()
            val tin = db.sequenceOf(TinTucs).toList().firstOrNull { it.id == id }
            if (tin != null) {
                tin.tieuDe = body["tieuDe"] ?: tin.tieuDe
                tin.noiDung = body["noiDung"] ?: tin.noiDung
                tin.flushChanges()
                call.respond(mapOf("status" to "SUCCESS", "message" to "Da cap nhat tin tuc"))
            } else { call.respond(HttpStatusCode.NotFound, mapOf("status" to "ERROR")) }
        }
        delete("/tin-tuc/{id}") {
            val id = call.parameters["id"] ?: ""
            db.sequenceOf(TinTucs).toList().firstOrNull { it.id == id }?.delete()
            call.respond(mapOf("status" to "SUCCESS"))
        }

        // ===== QUY TRINH NHAP HOC =====
        post("/quy-trinh") {
            val body = call.receive<Map<String, String>>()
            val qt = QuyTrinhNhapHoc {
                id = body["id"] ?: "QT_"
                noiDung = body["noiDung"]
                truongMaTruong = body["truongMaTruong"]
            }
            db.sequenceOf(QuyTrinhNhapHocs).add(qt)
            call.respond(HttpStatusCode.Created, mapOf("status" to "SUCCESS", "message" to "Da them quy trinh"))
        }
        put("/quy-trinh/{id}") {
            val id = call.parameters["id"] ?: ""
            val body = call.receive<Map<String, String>>()
            val qt = db.sequenceOf(QuyTrinhNhapHocs).toList().firstOrNull { it.id == id }
            if (qt != null) {
                qt.noiDung = body["noiDung"] ?: qt.noiDung
                qt.flushChanges()
                call.respond(mapOf("status" to "SUCCESS", "message" to "Da cap nhat quy trinh"))
            } else { call.respond(HttpStatusCode.NotFound, mapOf("status" to "ERROR")) }
        }

        // ===== HOC PHI =====
        post("/hoc-phi") {
            val body = call.receive<Map<String, String>>()
            val hp = HocPhi {
                id = body["id"] ?: "HP_"
                soTien = body["soTien"]
                namHoc = body["namHoc"]
                chuongTrinhHocId = body["chuongTrinhHocId"]
            }
            db.sequenceOf(HocPhis).add(hp)
            call.respond(HttpStatusCode.Created, mapOf("status" to "SUCCESS", "message" to "Da them hoc phi"))
        }
        put("/hoc-phi/{id}") {
            val id = call.parameters["id"] ?: ""
            val body = call.receive<Map<String, String>>()
            val hp = db.sequenceOf(HocPhis).toList().firstOrNull { it.id == id }
            if (hp != null) {
                hp.soTien = body["soTien"] ?: hp.soTien
                hp.namHoc = body["namHoc"] ?: hp.namHoc
                hp.flushChanges()
                call.respond(mapOf("status" to "SUCCESS", "message" to "Da cap nhat hoc phi"))
            } else { call.respond(HttpStatusCode.NotFound, mapOf("status" to "ERROR")) }
        }

        // ===== YEU CAU DAU VAO =====
        post("/yeu-cau-dau-vao") {
            val body = call.receive<Map<String, String>>()
            val yc = YeuCauDauVao {
                id = body["id"] ?: "YC_"
                khoi = body["khoi"]
                diemTong = body["diemTong"]
                nganhHocMaNganh = body["nganhHocMaNganh"]
            }
            db.sequenceOf(YeuCauDauVaos).add(yc)
            call.respond(HttpStatusCode.Created, mapOf("status" to "SUCCESS", "message" to "Da them yeu cau xet tuyen"))
        }
        put("/yeu-cau-dau-vao/{id}") {
            val id = call.parameters["id"] ?: ""
            val body = call.receive<Map<String, String>>()
            val yc = db.sequenceOf(YeuCauDauVaos).toList().firstOrNull { it.id == id }
            if (yc != null) {
                yc.khoi = body["khoi"] ?: yc.khoi
                yc.diemTong = body["diemTong"] ?: yc.diemTong
                yc.flushChanges()
                call.respond(mapOf("status" to "SUCCESS", "message" to "Da cap nhat"))
            } else { call.respond(HttpStatusCode.NotFound, mapOf("status" to "ERROR")) }
        }

        // ===== TAI KHOAN =====
        get("/tai-khoan") {
            val list = db.sequenceOf(TaiKhoans).toList().map {
                mapOf("id" to it.id, "username" to it.username, "role" to it.role)
            }
            call.respond(mapOf("status" to "SUCCESS", "data" to list))
        }
    }
}
