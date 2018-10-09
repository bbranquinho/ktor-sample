package org.wpattern.ktor.event

import org.wpattern.ktor.commons.fromJson
import org.wpattern.ktor.commons.toJson
import org.wpattern.ktor.exceptions.NotFoundException
import org.wpattern.ktor.product.ProductEvent
import java.io.File

abstract class EventRepository<T>(val eventName: String, val folderPath: String) {

    init {
        File(folderPath).apply {
            if (!exists()) {
                mkdirs()
            }
        }
    }

    fun create(aggregateId: String, event: T) {
        File(buildFilePath(aggregateId))
                .apply { appendText("${event.toJson()}\n") }
    }

    fun append(aggregateId: String, event: T) {
        File(buildFilePath(aggregateId))
                .apply { if (!exists()) throw NotFoundException(aggregateId = aggregateId) }
                .apply { appendText("${event.toJson()}\n") }
    }

    fun findAll(aggregateId: String): List<ProductEvent> =
        File(buildFilePath(aggregateId))
                .apply { if (!exists()) throw NotFoundException(aggregateId = aggregateId) }
                .readLines()
                .map { it.fromJson<ProductEvent>() }

    private fun buildFilePath(aggregateId: String) =
            "$folderPath/$eventName-$aggregateId"
}