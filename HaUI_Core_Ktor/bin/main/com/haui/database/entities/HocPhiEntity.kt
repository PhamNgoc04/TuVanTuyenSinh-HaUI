package com.haui.database.entities

import org.ktorm.schema.Table
import org.ktorm.schema.varchar
import org.ktorm.entity.Entity

interface HocPhi : Entity<HocPhi> {
    companion object : Entity.Factory<HocPhi>()
    var id: String
    var soTien: String?
    var namHoc: String?
    var chuongTrinhHocId: String?
}
object HocPhis : Table<HocPhi>("HocPhi") {
    val id = varchar("id").primaryKey().bindTo { it.id }
    val soTien = varchar("soTien").bindTo { it.soTien }
    val namHoc = varchar("namHoc").bindTo { it.namHoc }
    val chuongTrinhHocId = varchar("ChuongTrinhHocid").bindTo { it.chuongTrinhHocId }
}
