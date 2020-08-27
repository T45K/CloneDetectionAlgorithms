package io.github.t45k.cda.representaion

abstract class RepresentationFactory<R>(protected val requireNormalization: Boolean) {
    abstract fun create(raw: String): R
}