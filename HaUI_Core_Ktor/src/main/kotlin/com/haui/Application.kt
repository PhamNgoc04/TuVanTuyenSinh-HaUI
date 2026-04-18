package com.haui

import com.haui.routing.*
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.gson.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    install(ContentNegotiation) {
        gson { setPrettyPrinting() }
    }

    // Global error handler - bat moi exception khong xu ly duoc
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            val msg = cause.cause?.message ?: cause.message ?: cause.javaClass.simpleName
            call.respond(
                HttpStatusCode.InternalServerError,
                mapOf("status" to "ERROR", "message" to "Server error: $msg")
            )
        }
    }

    routing {
        get("/") {
            call.respondText("HaUI Core API - Ktor Backend v1.0 - All systems online!")
        }
        authRouting()
        truongRouting()
        nganhHocRouting()
        hocPhiRouting()
        nguoiDungRouting()
        adminRouting()
    }
}
