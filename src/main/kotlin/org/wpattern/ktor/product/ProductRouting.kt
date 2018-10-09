package org.wpattern.ktor.product

import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.delete
import io.ktor.routing.get
import io.ktor.routing.patch
import io.ktor.routing.post
import io.ktor.routing.put
import org.koin.ktor.ext.inject

private const val PRODUCT_BASE_PATH = "/product"

fun Routing.productRouting() {
    val productService: ProductService by inject()

    post("$PRODUCT_BASE_PATH") {
        val product = call.receive<ProductRequest>()
        val aggregateId = productService.create(product.toEvent())
        call.respond(HttpStatusCode.Created, AggregateIdResponse(aggregateId))
    }

    get("$PRODUCT_BASE_PATH/{id}") {
        val aggregateId = call.parameters["id"]!!
        call.respond(productService.findAll(aggregateId))
    }

    patch("$PRODUCT_BASE_PATH/{id}") {
        val aggregateId = call.parameters["id"]!!
        val product = call.receive<ProductRequest>()
        productService.save(aggregateId, product.toEvent())
        call.respond(HttpStatusCode.Created)
    }

}

data class ProductRequest(val name: String, val price: MoneyRequest, val storeId: String) {
    fun toEvent() =
            ProductEvent(name = name, price = price.toEvent(), storeId = storeId)
}

data class MoneyRequest(val value: Int, val scale: Int, val currency: String) {
    fun toEvent() =
            ProductEvent.Money(value = value, scale = scale, currency = currency)
}

data class AggregateIdResponse(val id: String)
