package io.github.t45k.cda.algorithm

interface Algorithm<T> {
    fun compare(representation1: T, representation2: T): Double
}