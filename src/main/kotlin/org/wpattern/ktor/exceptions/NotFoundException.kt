package org.wpattern.ktor.exceptions

class NotFoundException(val aggregateId: String = ""): RuntimeException("AggregateID not found.")
