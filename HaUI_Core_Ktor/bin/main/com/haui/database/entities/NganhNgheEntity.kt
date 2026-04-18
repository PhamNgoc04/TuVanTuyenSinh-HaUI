package com.haui.database.entities

import org.ktorm.schema.Table
import org.ktorm.schema.varchar
import org.ktorm.entity.Entity

interface NganhNghe : Entity<NganhNghe> {
    companion object : Entity.Factory<NganhNghe>()
    var maNghe: String
    var tenNghe: String
    var mucLuong: String?
    var tinhTrang: String?
    var nganhHocMaNganh: String?
}
object NganhNghes : Table<NganhNghe>("NganhNghe") {
    val maNghe = varchar("maNghe").primaryKey().bindTo { it.maNghe }
    val tenNghe = varchar("tenNghe").bindTo { it.tenNghe }
    val mucLuong = varchar("mucLuong").bindTo { it.mucLuong }
    val tinhTrang = varchar("tinhTrang").bindTo { it.tinhTrang }
    val nganhHocMaNganh = varchar("NganhHocmaNganh").bindTo { it.nganhHocMaNganh }
}
