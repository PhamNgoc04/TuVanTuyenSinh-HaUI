package com.haui.database.entities

import org.ktorm.schema.Table
import org.ktorm.schema.varchar
import org.ktorm.entity.Entity

interface HocBong : Entity<HocBong> {
    companion object : Entity.Factory<HocBong>()
    var id: String
    var loaiHb: String?
    var diemYc: String?
    var hanhKiemYc: String?
    var chuongTrinhHocId: String?
}
object HocBongs : Table<HocBong>("HocBong") {
    val id = varchar("id").primaryKey().bindTo { it.id }
    val loaiHb = varchar("loaiHb").bindTo { it.loaiHb }
    val diemYc = varchar("diemYc").bindTo { it.diemYc }
    val hanhKiemYc = varchar("hanhKiemYc").bindTo { it.hanhKiemYc }
    val chuongTrinhHocId = varchar("ChuongTrinhHocid").bindTo { it.chuongTrinhHocId }
}
