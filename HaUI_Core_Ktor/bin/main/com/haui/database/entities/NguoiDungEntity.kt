package com.haui.database.entities

import org.ktorm.schema.Table
import org.ktorm.schema.varchar
import org.ktorm.entity.Entity

interface NguoiDung : Entity<NguoiDung> {
    companion object : Entity.Factory<NguoiDung>()
    var id: String
    var ten: String?
    var avatar: String?
    var email: String?
    var vaiTro: String?
    var taiKhoanId: String?
}
object NguoiDungs : Table<NguoiDung>("NguoiDung") {
    val id = varchar("id").primaryKey().bindTo { it.id }
    val ten = varchar("ten").bindTo { it.ten }
    val avatar = varchar("avatar").bindTo { it.avatar }
    val email = varchar("email").bindTo { it.email }
    val vaiTro = varchar("vaiTro").bindTo { it.vaiTro }
    val taiKhoanId = varchar("TaiKhoanid").bindTo { it.taiKhoanId }
}
