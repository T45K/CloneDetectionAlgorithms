package io.github.t45k.cda.algorithm

import io.github.t45k.cda.CodeFragment
import io.github.t45k.cda.CodeFragmentCollector
import org.junit.Test
import java.io.File


internal class IntersectionProportionTest{


    @Test
    fun testType1() {
        val codeFragments: List<CodeFragment> =
            CodeFragmentCollector().collect(File("./src/test/resources/examples/Type1.java"))
        val similarity = IntersectionProportion().compare(codeFragments[0].toBugOfToken(), codeFragments[1].toBugOfToken())
        println(similarity)
    }

    @Test
    fun testType2() {
        val codeFragments: List<CodeFragment> =
            CodeFragmentCollector().collect(File("./src/test/resources/examples/Type2.java"))
        val similarity = IntersectionProportion().compare(codeFragments[0].toBugOfToken(), codeFragments[1].toBugOfToken())
        println(similarity)
    }

    @Test
    fun testType3() {
        val codeFragments: List<CodeFragment> =
            CodeFragmentCollector().collect(File("./src/test/resources/examples/Type3.java"))
        val similarity = IntersectionProportion().compare(codeFragments[0].toBugOfToken(), codeFragments[1].toBugOfToken())
        println(similarity)
    }

    @Test
    fun testType4() {
        val codeFragments: List<CodeFragment> =
            CodeFragmentCollector().collect(File("./src/test/resources/examples/Type4.java"))
        val similarity = IntersectionProportion().compare(codeFragments[0].toBugOfToken(), codeFragments[1].toBugOfToken())
        println(similarity)
    }
}
