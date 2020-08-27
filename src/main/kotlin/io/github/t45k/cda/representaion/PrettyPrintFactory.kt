package io.github.t45k.cda.representaion

import org.eclipse.core.runtime.NullProgressMonitor
import org.eclipse.jdt.core.dom.AST
import org.eclipse.jdt.core.dom.ASTNode
import org.eclipse.jdt.core.dom.ASTParser
import org.eclipse.jdt.core.dom.ASTVisitor
import org.eclipse.jdt.core.dom.AssertStatement
import org.eclipse.jdt.core.dom.BreakStatement
import org.eclipse.jdt.core.dom.CatchClause
import org.eclipse.jdt.core.dom.ContinueStatement
import org.eclipse.jdt.core.dom.DoStatement
import org.eclipse.jdt.core.dom.EnhancedForStatement
import org.eclipse.jdt.core.dom.ExpressionStatement
import org.eclipse.jdt.core.dom.ForStatement
import org.eclipse.jdt.core.dom.IfStatement
import org.eclipse.jdt.core.dom.ReturnStatement
import org.eclipse.jdt.core.dom.Statement
import org.eclipse.jdt.core.dom.SwitchStatement
import org.eclipse.jdt.core.dom.SynchronizedStatement
import org.eclipse.jdt.core.dom.ThrowStatement
import org.eclipse.jdt.core.dom.TryStatement
import org.eclipse.jdt.core.dom.TypeDeclarationStatement
import org.eclipse.jdt.core.dom.VariableDeclarationStatement
import org.eclipse.jdt.core.dom.WhileStatement
import org.eclipse.jdt.core.dom.YieldStatement

class PrettyPrintFactory(requireNormalization: Boolean = true) : RepresentationFactory<List<Int>>(requireNormalization) {

    override fun create(raw: String): List<Int> =
        ASTParser.newParser(AST.JLS14)
            .apply { this.setKind(ASTParser.K_STATEMENTS) }
            .apply { this.setSource(raw.toCharArray()) }
            .createAST(NullProgressMonitor())
            .let { astNode: ASTNode ->
                StatementVisitor(requireNormalization)
                    .apply { astNode.accept(this) }
                    .prettyPrintedStatements
            }
            .map { it.hashCode() }
            .toList()

    private class StatementVisitor(normalize: Boolean) : ASTVisitor() {
        val prettyPrintedStatements: MutableList<List<Int>> = mutableListOf()
        private val tokenSequenceFactory: TokenSequenceFactory = TokenSequenceFactory(normalize)

        override fun visit(node: AssertStatement?): Boolean {
            prettyPrintedStatements.add(tokenSequenceFactory.create(node.toString()))
            return super.visit(node)
        }

        override fun visit(node: BreakStatement?): Boolean {
            prettyPrintedStatements.add(tokenSequenceFactory.create(node.toString()))
            return super.visit(node)
        }

        override fun visit(node: ContinueStatement?): Boolean {
            prettyPrintedStatements.add(tokenSequenceFactory.create(node.toString()))
            return super.visit(node)
        }

        override fun visit(node: DoStatement?): Boolean {
            prettyPrintedStatements.add(tokenSequenceFactory.create("do"))
            return super.visit(node)
        }

        override fun visit(node: EnhancedForStatement?): Boolean {
            prettyPrintedStatements.add(tokenSequenceFactory.create("for ( ${node!!.parameter} : ${node.expression} )"))
            return super.visit(node)
        }

        override fun visit(node: ExpressionStatement?): Boolean {
            prettyPrintedStatements.add(tokenSequenceFactory.create(node.toString()))
            return super.visit(node)
        }

        override fun visit(node: ForStatement?): Boolean {
            prettyPrintedStatements.add(tokenSequenceFactory.create("for ( ${node!!.initializers()} ; ${node.expression} ; ${node.updaters()} )"))
            return super.visit(node)
        }

        override fun visit(node: IfStatement?): Boolean {
            prettyPrintedStatements.add(tokenSequenceFactory.create("if ( ${node!!.expression} )"))
            return super.visit(node)
        }

        override fun visit(node: ReturnStatement?): Boolean {
            prettyPrintedStatements.add(tokenSequenceFactory.create(node.toString()))
            return super.visit(node)
        }

        override fun visit(node: SwitchStatement?): Boolean {
            prettyPrintedStatements.add(tokenSequenceFactory.create("switch ( ${node!!.expression} )"))
            return super.visit(node)
        }

        override fun visit(node: SynchronizedStatement?): Boolean {
            prettyPrintedStatements.add(tokenSequenceFactory.create("synchronized"))
            return super.visit(node)
        }

        override fun visit(node: ThrowStatement?): Boolean {
            prettyPrintedStatements.add(tokenSequenceFactory.create(node.toString()))
            return super.visit(node)
        }

        override fun visit(node: TryStatement?): Boolean {
            prettyPrintedStatements.add(tokenSequenceFactory.create("try ( ${
                node!!.resources().joinToString(" , ") { it.toString() }
            } )"))

            node.body.accept(this)

            node.catchClauses().forEach { (it as CatchClause).accept(this) }

            if (node.finally != null) {
                prettyPrintedStatements.add(tokenSequenceFactory.create("finally"))
                node.finally.statements().forEach { (it as Statement).accept(this) }
            }

            return false
        }

        override fun visit(node: CatchClause?): Boolean {
            prettyPrintedStatements.add(tokenSequenceFactory.create("catch ( ${node!!.exception} )"))
            return super.visit(node)
        }

        // TODO
        override fun visit(node: TypeDeclarationStatement?): Boolean {
            return super.visit(node)
        }

        override fun visit(node: VariableDeclarationStatement?): Boolean {
            prettyPrintedStatements.add(tokenSequenceFactory.create(node.toString()))
            return super.visit(node)
        }

        override fun visit(node: WhileStatement?): Boolean {
            prettyPrintedStatements.add(tokenSequenceFactory.create("while ( ${node!!.expression} )"))
            return super.visit(node)
        }

        override fun visit(node: YieldStatement?): Boolean {
            prettyPrintedStatements.add(tokenSequenceFactory.create(node.toString()))
            return super.visit(node)
        }
    }
}