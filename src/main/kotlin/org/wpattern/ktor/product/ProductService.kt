package org.wpattern.ktor.product

interface ProductService {

    fun create(event: ProductEvent): String

    fun save(aggregateId: String, event: ProductEvent)

    fun findAll(aggregateId: String): List<ProductEvent>

}
