package org.example.detekt

import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.RuleSet
import io.gitlab.arturbosch.detekt.api.RuleSetProvider

class CccRuleSetProvider : RuleSetProvider {
    override val ruleSetId: String = "CccRuleSet"

    override fun instance(config: Config): RuleSet {
        return RuleSet(
            ruleSetId,
            listOf(
                BooleanNotOverExclamation(config),
            ),
        )
    }
}
