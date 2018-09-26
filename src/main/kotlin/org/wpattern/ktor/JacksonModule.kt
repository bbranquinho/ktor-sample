package org.wpattern.ktor

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.jackson.jackson
import io.ktor.routing.Routing
import io.ktor.routing.routing

fun jacksonModule(application: Application) {
    application.routing(::install)
}

fun install(routing: Routing) {
    routing.install(ContentNegotiation, ::configuration)
}

fun configuration(configuration: ContentNegotiation.Configuration) {
    configuration.jackson(::objectMapper)
}

fun objectMapper(objectMapper: ObjectMapper) {
    objectMapper.enable(SerializationFeature.INDENT_OUTPUT)
}