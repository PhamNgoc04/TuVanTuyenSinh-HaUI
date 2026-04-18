package com.haui.database

import org.ktorm.database.Database
import org.ktorm.support.mysql.MySqlDialect

object DatabaseManager {
    val database = Database.connect(
        url = "jdbc:mysql://localhost:3306/hau_tuyensinh?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC",
        driver = "com.mysql.cj.jdbc.Driver",
        user = "root",
        password = "123456789",
        dialect = MySqlDialect()
    )
}
