package org.wpattern.ktor.person

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.routing

private val personService = PersonService()

fun Routing.personRouting() {
    get("/person") {
        call.respond(personService.findAll())
    }
}