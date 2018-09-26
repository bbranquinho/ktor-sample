package org.wpattern.ktor

import io.ktor.application.Application
import io.ktor.routing.Routing
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.wpattern.ktor.person.personRouting

fun main() {
    embeddedServer(Netty, 8080, module = ::module).start(wait = true)
}

fun module(application: Application) {
    jacksonModule(application)
    application.routing(::routing)
}

fun routing(routing: Routing) {
    routing.personRouting()
}

