package com.kian.yun.sheetshow.sheet.domain.repository.support

class SimpleFilterable(
    private val conditions: List<Condition>,
    private val conditionOption: ConditionOption
) : Filterable {
    companion object {
        fun of(condition: List<Condition>, conditionOption: ConditionOption)
        = SimpleFilterable(condition, conditionOption)
    }
    override fun getConditions(): List<Condition>
    = this.conditions

    override fun getConditionOption(): ConditionOption
    = this.conditionOption
}