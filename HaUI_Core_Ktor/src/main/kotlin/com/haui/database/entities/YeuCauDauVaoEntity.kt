package com.haui.database.entities

import org.ktorm.schema.Table
import org.ktorm.schema.varchar
import org.ktorm.entity.Entity

interface YeuCauDauVao : Entity<YeuCauDauVao> {
    companion object : Entity.Factory<YeuCauDauVao>()
    var id: String
    var khoi: String?
    var diemTong: String?
    var nganhHocMaNganh: String?
}
object YeuCauDauVaos : Table<YeuCauDauVao>("YeuCauDauVao") {
    val id = varchar("id").primaryKey().bindTo { it.id }
    val khoi = varchar("khoi").bindTo { it.khoi }
    val diemTong = varchar("diemTong").bindTo { it.diemTong }
    val nganhHocMaNganh = varchar("NganhHocmaNganh").bindTo { it.nganhHocMaNganh }
}
