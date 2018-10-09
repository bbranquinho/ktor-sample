package org.wpattern.ktor.config

import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.StatusPages
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.Routing
import org.wpattern.ktor.exceptions.NotFoundException

fun Routing.installHttpStatusHandler() {
    install(StatusPages) {
        exception<NotFoundException> {
            call.respond(HttpStatusCode.NotFound)
        }
    }
}