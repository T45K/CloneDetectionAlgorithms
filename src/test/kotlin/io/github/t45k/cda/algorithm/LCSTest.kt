package io.github.t45k.cda.algorithm

import io.github.t45k.cda.CodeFragment
import io.github.t45k.cda.CodeFragmentCollector
import java.io.File
import kotlin.test.Test

internal class LCSTest {

    @Test
    fun testPrettyPrintType1() {
        val codeFragments: List<CodeFragment> =
            CodeFragmentCollector().collect(File("./src/test/resources/examples/Type1.java"))
        val similarity = LCS().compare(codeFragments[0].toPrettyPrintedText(), codeFragments[1].toPrettyPrintedText())
        println(similarity)
    }

    @Test
    fun testTokenSequenceType1() {
        val codeFragments: List<CodeFragment> =
            CodeFragmentCollector().collect(File("./src/test/resources/examples/Type1.java"))
        val similarity = LCS().compare(codeFragments[0].toTokenSequence(), codeFragments[1].toTokenSequence())
        println(similarity)
    }

    @Test
    fun testPrettyPrintType2() {
        val codeFragments: List<CodeFragment> =
            CodeFragmentCollector().collect(File("./src/test/resources/examples/Type2.java"))
        val similarity = LCS().compare(codeFragments[0].toPrettyPrintedText(), codeFragments[1].toPrettyPrintedText())
        println(similarity)
    }

    @Test
    fun testTokenSequenceType2() {
        val codeFragments: List<CodeFragment> =
            CodeFragmentCollector().collect(File("./src/test/resources/examples/Type2.java"))
        val similarity = LCS().compare(codeFragments[0].toTokenSequence(), codeFragments[1].toTokenSequence())
        println(similarity)
    }

    @Test
    fun testPrettyPrintType3() {
        val codeFragments: List<CodeFragment> =
            CodeFragmentCollector().collect(File("./src/test/resources/examples/Type3.java"))
        val similarity = LCS().compare(codeFragments[0].toPrettyPrintedText(), codeFragments[1].toPrettyPrintedText())
        println(similarity)
    }

    @Test
    fun testTokenSequenceType3() {
        val codeFragments: List<CodeFragment> =
            CodeFragmentCollector().collect(File("./src/test/resources/examples/Type3.java"))
        val similarity = LCS().compare(codeFragments[0].toTokenSequence(), codeFragments[1].toTokenSequence())
        println(similarity)
    }

    @Test
    fun testPrettyPrintType4() {
        val codeFragments: List<CodeFragment> =
            CodeFragmentCollector().collect(File("./src/test/resources/examples/Type4.java"))
        val similarity = LCS().compare(codeFragments[0].toPrettyPrintedText(), codeFragments[1].toPrettyPrintedText())
        println(similarity)
    }

    @Test
    fun testTokenSequenceType4() {
        val codeFragments: List<CodeFragment> =
            CodeFragmentCollector().collect(File("./src/test/resources/examples/Type4.java"))
        val similarity = LCS().compare(codeFragments[0].toTokenSequence(), codeFragments[1].toTokenSequence())
        println(similarity)
    }
}
