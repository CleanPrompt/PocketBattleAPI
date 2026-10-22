// src/Application.kt
package org.jetbrains.amper.ktor                    // Deze package komt overeen met de mainClass in module.yaml

import io.ktor.server.application.*

fun main(args: Array<String>) {                     // Dit is het startpunt van de applicatie. Het start de Ktor-server via de Netty-engine.
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {                          // Dit is de applicatiemodule waarin Ktor-functionaliteit wordt geconfigureerd.
    configureRouting()
}