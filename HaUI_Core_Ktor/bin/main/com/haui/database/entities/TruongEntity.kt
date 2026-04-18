package com.haui.database.entities

import org.ktorm.schema.Table
import org.ktorm.schema.varchar
import org.ktorm.schema.text
import org.ktorm.entity.Entity

// --- 1. Thực thể (Entity) ---
interface Truong : Entity<Truong> {
    companion object : Entity.Factory<Truong>()
    val maTruong: String
    var tenTruong: String
    var moTa: String?
    var thanhPho: String?
    var quan: String?
    var duong: String?
}

// --- 2. Bảng ánh xạ DB (Table) ---
object Truongs : Table<Truong>("Truong") {
    val maTruong = varchar("maTruong").primaryKey().bindTo { it.maTruong }
    val tenTruong = varchar("tenTruong").bindTo { it.tenTruong }
    val moTa = text("moTa").bindTo { it.moTa }
    val thanhPho = varchar("thanhPho").bindTo { it.thanhPho }
    val quan = varchar("quan").bindTo { it.quan }
    val duong = varchar("duong").bindTo { it.duong }
}
