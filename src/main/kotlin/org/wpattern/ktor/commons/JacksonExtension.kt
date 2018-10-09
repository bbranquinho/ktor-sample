package org.wpattern.ktor.commons

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

var kObjectMapper: ObjectMapper = jacksonObjectMapper()

//fun <T> T.toJson(): String =
//        kObjectMapper.writeValueAsString(this)
//
//inline fun <reified T> String.fromJson(): T =
//        kObjectMapper.readValue(this, T::class.java)
