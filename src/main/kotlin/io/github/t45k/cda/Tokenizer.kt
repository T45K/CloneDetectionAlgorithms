package io.github.t45k.cda

import org.eclipse.jdt.core.ToolFactory
import org.eclipse.jdt.core.compiler.IScanner
import org.eclipse.jdt.core.compiler.ITerminalSymbols.*

class Tokenizer(private val requireNormalization: Boolean = true) {
    fun tokenize( raw: String): List<Int> =
        ToolFactory.createScanner(false, false, true, false)
            .apply { this.source = raw.toCharArray() }
            .let { scanner: IScanner ->
                generateSequence { 0 }
                    .map { scanner.nextToken }
                    .takeWhile { it != TokenNameEOF }
                    .map { normalize(it, scanner) }
                    .toList()
            }

    private fun normalize(tokenId: Int, scanner: IScanner): Int =
        if (tokenId.isTarget()) {
            0
        } else {
            scanner.rawTokenSource.hashCode()
        }

    private fun Int.isTarget(): Boolean =
        if (requireNormalization) {
            when (this) {
                TokenNameIdentifier,
                TokenNameCharacterLiteral,
                TokenNameDoubleLiteral,
                TokenNameIntegerLiteral,
                TokenNameLongLiteral,
                TokenNameStringLiteral,
                TokenNameFloatingPointLiteral,
                TokenNametrue,
                TokenNamefalse -> true
                else -> false
            }
        } else {
            false
        }
}
