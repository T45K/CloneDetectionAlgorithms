package io.github.t45k.cda

import org.eclipse.core.runtime.NullProgressMonitor
import org.eclipse.jdt.core.dom.AST
import org.eclipse.jdt.core.dom.ASTNode
import org.eclipse.jdt.core.dom.ASTParser
import org.eclipse.jdt.core.dom.ASTVisitor
import org.eclipse.jdt.core.dom.Block
import org.eclipse.jdt.core.dom.CompilationUnit
import org.eclipse.jdt.core.dom.MethodDeclaration
import java.io.File
import java.util.concurrent.atomic.AtomicInteger

class CodeFragmentCollector {
    fun collect(file: File): List<CodeFragment> =
        ASTParser.newParser(AST.JLS14)
            .apply { this.setSource(file.readText().toCharArray()) }
            .createAST(NullProgressMonitor())
            .let { astNode: ASTNode ->
                MethodVisitor(astNode as CompilationUnit, file)
                    .apply { astNode.accept(this) }
                    .codeFragments
            }.toList()

    private class MethodVisitor(private val compilationUnit: CompilationUnit, private val file: File) : ASTVisitor() {
        val codeFragments: MutableList<CodeFragment> = mutableListOf()
        private val id: AtomicInteger = AtomicInteger(1)

        override fun visit(node: MethodDeclaration?): Boolean {
            val body: Block = node!!.body ?: return true
            codeFragments.add(
                CodeFragment(
                    id.getAndIncrement(),
                    file,
                    compilationUnit.getLineNumber(body.startPosition),
                    compilationUnit.getLineNumber(body.startPosition + body.length),
                    body.toString()
                )
            )
            return true
        }
    }
}
