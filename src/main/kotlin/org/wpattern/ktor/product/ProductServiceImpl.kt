package org.wpattern.ktor.product

import java.util.UUID

class ProductServiceImpl(private val eventRepository: ProductFileEventRepository) : ProductService {

    override fun create(event: ProductEvent): String {
        val aggregateId = UUID.randomUUID().toString()
        eventRepository.create(aggregateId = aggregateId, event = event)
        return aggregateId
    }

    override fun save(aggregateId: String, event: ProductEvent) {
        eventRepository.append(aggregateId = aggregateId, event = event)
    }

    override fun findAll(aggregateId: String): List<ProductEvent> =
            eventRepository.findAll(aggregateId)

}