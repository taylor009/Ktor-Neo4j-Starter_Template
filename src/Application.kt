package com.duw

import io.ktor.application.*
import io.ktor.features.CallLogging
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.get
import io.ktor.routing.routing
import java.io.File
import java.util.*

fun main(args: Array<String>): Unit = io.ktor.server.tomcat.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    intercept(ApplicationCallPipeline.Features){
        val requestId = UUID.randomUUID()
        logger.attach("req.Id", requestId.toString()) {
            logger.info("Interceptor[start]")
            proceed()
            logger.info("Interceptor[end]")
        }
    }
    install(CallLogging)

    routing {
        get("/") {
            logger.info("Respond[start]")
            call.respondText("HELLO WORLD")
            logger.info("Respond[end]")
        }
    }
}

