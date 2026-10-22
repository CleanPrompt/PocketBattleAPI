// src/Routing.kt
package org.jetbrains.amper.ktor                                // Dezelfde package als Application.kt.

import io.ktor.server.application.*                             // Geeft toegang tot de Ktor Application-functionaliteit.
import io.ktor.server.response.*                                // Bevat functies om HTTP-responses terug te sturen.
import io.ktor.server.routing.*                                 // Bevat functies voor het definiëren van API-routes.

fun Application.configureRouting() {                            // Configureert de HTTP-endpoints van onze applicatie.
    routing {                                                   // Start de configuratie van de routes.

        get("/") {                                           // Verwerkt een HTTP GET-request naar de hoofdroute.
            call.respondText("Welcome to PocketBattleAPI!")     // Stuurt een welkomstbericht terug.
        }

    }
}