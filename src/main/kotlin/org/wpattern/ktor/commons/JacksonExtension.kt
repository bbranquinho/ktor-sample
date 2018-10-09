package org.wpattern.ktor.commons

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import kotlin.reflect.full.isSuperclassOf

var kObjectMapper: ObjectMapper = jacksonObjectMapper()

fun <T> T.toJson(): String =
        kObjectMapper.writeValueAsString(this)

inline fun <reified T> String.fromJson(): T =
        if (isListOrMap<T>()) {
            kObjectMapper.readValue(this, object : TypeReference<T>() {})
        } else {
            kObjectMapper.readValue(this, T::class.java)
        }

inline fun <reified T> isListOrMap() =
        T::class.isSuperclassOf(List::class) || T::class.isSuperclassOf(Map::class)
