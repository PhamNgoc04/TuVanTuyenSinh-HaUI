package com.haui.database.entities

import org.ktorm.schema.Table
import org.ktorm.schema.varchar
import org.ktorm.schema.text
import org.ktorm.entity.Entity

interface NganhHoc : Entity<NganhHoc> {
    companion object : Entity.Factory<NganhHoc>()
    var maNganh: String          // var de co the set trong factory block
    var tenNganh: String
    var moTa: String?
    var coSo: String?
}

object NganhHocs : Table<NganhHoc>("NganhHoc") {
    val maNganh = varchar("maNganh").primaryKey().bindTo { it.maNganh }
    val tenNganh = varchar("tenNganh").bindTo { it.tenNganh }
    val moTa = text("moTa").bindTo { it.moTa }
    val coSo = varchar("coSo").bindTo { it.coSo }
}
