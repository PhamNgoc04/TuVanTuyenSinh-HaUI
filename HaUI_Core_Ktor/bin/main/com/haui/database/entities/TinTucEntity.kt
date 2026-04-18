package com.haui.database.entities

import org.ktorm.schema.Table
import org.ktorm.schema.varchar
import org.ktorm.schema.text
import org.ktorm.entity.Entity

interface TinTuc : Entity<TinTuc> {
    companion object : Entity.Factory<TinTuc>()
    var id: String
    var tieuDe: String
    var anh: String?
    var noiDung: String?
    var moTa: String?
    var truongMaTruong: String?
}
object TinTucs : Table<TinTuc>("TinTuc") {
    val id = varchar("id").primaryKey().bindTo { it.id }
    val tieuDe = varchar("tieuDe").bindTo { it.tieuDe }
    val anh = varchar("anh").bindTo { it.anh }
    val noiDung = text("noiDung").bindTo { it.noiDung }
    val moTa = varchar("moTa").bindTo { it.moTa }
    val truongMaTruong = varchar("TruongmaTruong").bindTo { it.truongMaTruong }
}
