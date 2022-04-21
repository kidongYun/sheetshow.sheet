package com.kian.yun.sheetshow.sheet.domain.repository.support

interface Filterable {
    fun getConditions(): List<Condition>

    fun getConditionOption() : ConditionOption
}