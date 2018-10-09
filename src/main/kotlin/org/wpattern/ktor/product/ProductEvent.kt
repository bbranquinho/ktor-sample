package org.wpattern.ktor.product

import org.wpattern.ktor.event.Event
import java.util.UUID

data class ProductEvent(val name: String, val price: Money, val storeId: String) : Event {

    data class Money(val value: Int, val scale: Int, val currency: String)

}
