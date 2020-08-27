package io.github.t45k.cda.algorithm

import kotlin.math.max
import kotlin.math.min

class LCS : Algorithm<List<Int>> {
    override fun compare(representation1: List<Int>, representation2: List<Int>): Double {
        val dpTable: Array<Array<Int>> = Array(representation1.size + 1) { Array(representation2.size + 1) { 0 } }
        for (i in 1..representation1.size) {
            for (j in 1..representation2.size) {
                if (representation1[i - 1] == representation2[j - 1]) {
                    dpTable[i][j] = dpTable[i - 1][j - 1] + 1
                } else {
                    dpTable[i][j] = max(dpTable[i - 1][j], dpTable[i][j - 1])
                }
            }
        }

        return dpTable[representation1.size][representation2.size].toDouble() / min(
            representation1.size,
            representation2.size
        ).toDouble()
    }
}
