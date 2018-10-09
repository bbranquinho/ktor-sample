package org.wpattern.ktor

import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.routing.routing
import io.ktor.server.engine.commandLineEnvironment
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.wpattern.ktor.product.productRouting
import org.wpattern.ktor.config.configKoin
import org.wpattern.ktor.config.installHttpStatusHandler
import org.wpattern.ktor.config.installJackson

fun main(args: Array<String>) {
    embeddedServer(Netty, commandLineEnvironment(args)).start(wait = true)
}

fun Application.start() {
    configKoin()
    install(CallLogging)

    routing {
        installHttpStatusHandler()
        installJackson()
        productRouting()
    }
}
