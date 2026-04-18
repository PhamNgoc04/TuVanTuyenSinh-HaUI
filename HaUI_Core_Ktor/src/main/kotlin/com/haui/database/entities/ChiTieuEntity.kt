package com.haui.database.entities

import org.ktorm.schema.Table
import org.ktorm.schema.varchar
import org.ktorm.entity.Entity

interface ChiTieu : Entity<ChiTieu> {
    companion object : Entity.Factory<ChiTieu>()
    val maChiTieu: String
    var nam: String?
    var soLuong: String?
    var phuongThuc: String?
    var nganhHocMaNganh: String?
}

object ChiTieus : Table<ChiTieu>("ChiTieu") {
    val maChiTieu = varchar("maChiTieu").primaryKey().bindTo { it.maChiTieu }
    val nam = varchar("nam").bindTo { it.nam }
    val soLuong = varchar("soLuong").bindTo { it.soLuong }
    val phuongThuc = varchar("phuongThuc").bindTo { it.phuongThuc }
    val nganhHocMaNganh = varchar("NganhHocmaNganh").bindTo { it.nganhHocMaNganh }
}
