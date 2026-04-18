package com.haui.database.entities

import org.ktorm.schema.Table
import org.ktorm.schema.varchar
import org.ktorm.schema.text
import org.ktorm.entity.Entity

interface QuyTrinhNhapHoc : Entity<QuyTrinhNhapHoc> {
    companion object : Entity.Factory<QuyTrinhNhapHoc>()
    var id: String
    var noiDung: String?
    var truongMaTruong: String?
}
object QuyTrinhNhapHocs : Table<QuyTrinhNhapHoc>("QuyTrinhNhapHoc") {
    val id = varchar("id").primaryKey().bindTo { it.id }
    val noiDung = text("noiDung").bindTo { it.noiDung }
    val truongMaTruong = varchar("TruongmaTruong").bindTo { it.truongMaTruong }
}
