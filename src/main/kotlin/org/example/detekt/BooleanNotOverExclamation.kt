package org.example.detekt

import io.gitlab.arturbosch.detekt.api.CodeSmell
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Issue
import io.gitlab.arturbosch.detekt.api.Rule
import io.gitlab.arturbosch.detekt.api.Severity
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.KtUnaryExpression

class BooleanNotOverExclamation(config: Config) : Rule(config) {
    override val issue = Issue(
        javaClass.simpleName,
        Severity.CodeSmell,
        "Use `Boolean.not()` instead of `!Boolean`, which is harder to maintain",
        Debt.FIVE_MINS,
    )

    override fun visitUnaryExpression(expression: KtUnaryExpression) {
        if (expression.operationToken == KtTokens.EXCL) {
            report(
                CodeSmell(
                    issue,
                    Entity.from(expression),
                    "Use Boolean.not() instead of ! operator"
                )
            )
        }
        super.visitUnaryExpression(expression)
    }
}
