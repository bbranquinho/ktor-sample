package org.wpattern.ktor.person

class PersonService {
    private val people = listOf(
            Person(name = "Branquinho"),
            Person(name = "Augusto")
    )

    fun findAll(): List<Person> =
            people

}