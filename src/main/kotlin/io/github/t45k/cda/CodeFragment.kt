package io.github.t45k.cda

import io.github.t45k.cda.representaion.PrettyPrintFactory
import io.github.t45k.cda.representaion.TokenSequenceFactory
import java.io.File

data class CodeFragment(
    val id: Int,
    val file: File,
    val startLine: Int,
    val endLine: Int,
    val raw: String,
) {
    private lateinit var prettyPrintedText: List<Int>
    private lateinit var tokenSequence: List<Int>
    private lateinit var bugOfToken: Map<Int, Int>

    fun toPrettyPrintedText(requireNormalization: Boolean = true): List<Int> =
        if (::prettyPrintedText.isInitialized) {
            prettyPrintedText
        } else {
            PrettyPrintFactory(requireNormalization).create(raw)
        }


    fun toTokenSequence(requireNormalization: Boolean = true): List<Int> =
        if (::tokenSequence.isInitialized) {
            tokenSequence
        } else {
            TokenSequenceFactory(requireNormalization).create(raw)
        }

    fun toBugOfToken(): Map<Int, Int> =
        if (::bugOfToken.isInitialized) {
            bugOfToken
        } else {
            BugOfToken().create(raw)
        }
}
