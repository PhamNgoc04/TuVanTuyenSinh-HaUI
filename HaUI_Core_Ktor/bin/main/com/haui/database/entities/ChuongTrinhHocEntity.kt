package com.haui.database.entities
import org.ktorm.schema.Table
import org.ktorm.schema.varchar
import org.ktorm.entity.Entity

interface ChuongTrinhHoc : Entity<ChuongTrinhHoc> {
    companion object : Entity.Factory<ChuongTrinhHoc>()
    var id: String
    var thoiGianHoc: String?
    var loai: String?
}
object ChuongTrinhHocs : Table<ChuongTrinhHoc>("ChuongTrinhHoc") {
    val id = varchar("id").primaryKey().bindTo { it.id }
    val thoiGianHoc = varchar("thoiGianHoc").bindTo { it.thoiGianHoc }
    val loai = varchar("loai").bindTo { it.loai }
}
