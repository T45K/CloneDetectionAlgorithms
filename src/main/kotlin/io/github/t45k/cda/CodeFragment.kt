package io.github.t45k.cda

import java.io.File

data class CodeFragment(
    val id:Int,
    val file: File,
    val startLine: Int,
    val endLine: Int,
    val raw: String,
) {
    private lateinit var prettyPrintedText: List<Int>
    private lateinit var tokenSequence: List<Int>

    fun toPrettyPrintedText(normalize: Boolean): List<Int> =
        if (::prettyPrintedText.isInitialized) {
            prettyPrintedText
        } else {
            PrettyPrinter(raw, normalize).prettyPrint()
        }


    fun toTokenSequence(normalize: Boolean = true): List<Int> =
        if (::tokenSequence.isInitialized) {
            tokenSequence
        } else {
            Tokenizer(normalize).tokenize(raw)
        }
}
