package org.wpattern.ktor.product

import org.wpattern.ktor.event.EventRepository

class ProductFileEventRepository(eventName: String, folderPath: String)
    : EventRepository<ProductEvent>(eventName = eventName, folderPath = folderPath)
