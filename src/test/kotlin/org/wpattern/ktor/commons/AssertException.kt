package org.wpattern.ktor.commons

import kotlin.test.assertTrue
import kotlin.test.fail

fun <E : Throwable> assertException(expectedException: Class<E>, expectedMessage: String, assertThat: (e: E) -> Boolean = { true }, block: () -> Unit) =
        try {
            block()
            fail("Expected exception [$expectedException] not thrown.")
        } catch (e: Throwable) {
            assertTrue(
                    message = "The expected message is [$expectedMessage], but throws the message [${e.message}].",
                    block = { expectedMessage == e.message }
            )

            if (expectedException != e.javaClass) {
                fail("The expected exception is [${expectedException.canonicalName}], but throws [${e.javaClass.canonicalName}].")
            } else {
                assertTrue(assertThat(e as E))
            }
        }
