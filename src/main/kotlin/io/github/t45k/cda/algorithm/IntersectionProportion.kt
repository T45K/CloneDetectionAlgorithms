package io.github.t45k.cda.algorithm

import kotlin.math.max
import kotlin.math.min

class IntersectionProportion : Algorithm<Map<Int, Int>> {
    override fun compare(representation1: Map<Int, Int>, representation2: Map<Int, Int>): Double {
        val intersection: Int =
            representation1.map { min(it.value, representation2[it.key] ?: 0) }.sum()

        val union: Int = representation1.map { max(it.value, representation2[it.key] ?: 0) }.sum() +
            representation2.map { if (representation2.containsKey(it.key)) 0 else it.value }.sum()
        return intersection.toDouble() / union.toDouble()
    }
}
