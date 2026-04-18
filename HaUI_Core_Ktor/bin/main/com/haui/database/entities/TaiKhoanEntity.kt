package com.haui.database.entities

import org.ktorm.schema.Table
import org.ktorm.schema.varchar
import org.ktorm.entity.Entity

interface TaiKhoan : Entity<TaiKhoan> {
    companion object : Entity.Factory<TaiKhoan>()
    var id: String
    var username: String
    var passwordHash: String
    var role: String
}

object TaiKhoans : Table<TaiKhoan>("TaiKhoan") {
    val id = varchar("id").primaryKey().bindTo { it.id }
    val username = varchar("username").bindTo { it.username }
    val passwordHash = varchar("password_hash").bindTo { it.passwordHash }
    val role = varchar("role").bindTo { it.role }
}
