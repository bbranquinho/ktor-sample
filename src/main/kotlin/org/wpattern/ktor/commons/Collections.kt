package org.wpattern.ktor.commons

fun <K, V> List<Map<K, V>>.accumulate(): Map<K, V> =
        this.fold(HashMap()) { accumulator, step -> accumulator += step; accumulator }
