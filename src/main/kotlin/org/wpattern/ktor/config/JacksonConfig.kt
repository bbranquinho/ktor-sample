package org.wpattern.ktor.config

import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.jackson.jackson
import io.ktor.routing.Routing

fun Routing.installJackson() {
    install(ContentNegotiation) {
        jackson { }
    }
}
