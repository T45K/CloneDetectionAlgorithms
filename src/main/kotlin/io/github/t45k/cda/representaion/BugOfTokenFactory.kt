package io.github.t45k.cda.representaion

class BugOfTokenFactory : RepresentationFactory<Map<Int, Int>>(false) {
    override fun create(raw: String): Map<Int, Int> =
        TokenSequenceFactory(false).create(raw)
            .groupBy { it }
            .map { it.key to it.value.size }
            .toMap()
}
