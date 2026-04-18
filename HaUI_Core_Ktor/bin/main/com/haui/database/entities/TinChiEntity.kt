package com.haui.database.entities
import org.ktorm.schema.Table
import org.ktorm.schema.varchar
import org.ktorm.entity.Entity

interface TinChi : Entity<TinChi> {
    companion object : Entity.Factory<TinChi>()
    var id: String
    var giaTien: String?
    var nganhHocMaNganh: String?
}
object TinChis : Table<TinChi>("TinChi") {
    val id = varchar("id").primaryKey().bindTo { it.id }
    val giaTien = varchar("giaTien").bindTo { it.giaTien }
    val nganhHocMaNganh = varchar("NganhHocmaNganh").bindTo { it.nganhHocMaNganh }
}
