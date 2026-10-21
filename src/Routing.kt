// src/Routing.kt
package org.jetbrains.amper.ktor

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import nl.pocketbattle.api.learning.runKotlinFundamentalsDemo

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Welcome to PocketBattleAPI!")
        }

        get("/learning/kotlin-fundamentals") {
            call.respondText(runKotlinFundamentalsDemo())
        }
    }
}