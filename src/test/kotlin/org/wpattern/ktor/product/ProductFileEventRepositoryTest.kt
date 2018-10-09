package org.wpattern.ktor.product

import org.junit.Test
import org.wpattern.ktor.commons.assertException
import org.wpattern.ktor.exceptions.NotFoundException
import java.util.UUID

class ProductFileEventRepositoryTest {

    private val defaultPath = "${System.getProperty("user.dir")}/target/events_test"

    private val productFileEventRepository = ProductFileEventRepository(eventName = "product", folderPath = defaultPath)

    @Test
    fun `Try to append an event that not exists, it must throw a NotFoundException`() {
        val aggregateId = UUID.randomUUID().toString()
        val price = Money(value = 1283, scale = 2, currency = "CAD")
        val event = ProductEvent("Name", price, UUID.randomUUID().toString())

        assertException(
                expectedException = NotFoundException::class.java,
                expectedMessage = "AggregateID not found.",
//                assertThat = { it.aggregateId == aggregateId },
                block = { productFileEventRepository.append(aggregateId = aggregateId, event = event) }
        )
    }

}