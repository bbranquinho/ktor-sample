package org.wpattern.ktor.event

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.wpattern.ktor.exceptions.NotFoundException
import org.wpattern.ktor.product.ProductEvent
import java.io.File

abstract class EventRepository<T>(val eventName: String, val folderPath: String) {

    private val objectMapper = jacksonObjectMapper()

    init {
        File(folderPath).apply {
            if (!exists()) {
                mkdirs()
            }
        }
    }

    fun create(aggregateId: String, event: T) {
        val json = objectMapper.writeValueAsString(event)

        File(buildFilePath(aggregateId))
                .apply { appendText(json + "\n") }
    }

    fun append(aggregateId: String, event: T) {
        val json = objectMapper.writeValueAsString(event)

        File(buildFilePath(aggregateId))
                .apply { if (!exists()) throw NotFoundException(aggregateId = aggregateId) }
                .apply { appendText(json + "\n") }
    }

    fun findAll(aggregateId: String): List<ProductEvent> =
        File(buildFilePath(aggregateId))
                .apply { if (!exists()) throw NotFoundException(aggregateId = aggregateId) }
                .readLines()
                .map { objectMapper.readValue(it, ProductEvent::class.java) }

    private fun buildFilePath(aggregateId: String) =
            "$folderPath/$eventName-$aggregateId"
}