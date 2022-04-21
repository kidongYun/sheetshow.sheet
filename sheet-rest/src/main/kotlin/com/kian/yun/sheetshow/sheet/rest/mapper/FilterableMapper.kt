package com.kian.yun.sheetshow.sheet.rest.mapper

import com.kian.yun.sheetshow.filterable.queryOptions.QueryOptions
import com.kian.yun.sheetshow.sheet.domain.repository.support.*
import org.springframework.stereotype.Component
import java.util.*

@Component
class FilterableMapper {
    fun ofDomain(src: com.kian.yun.sheetshow.sheet.rest.dto.Filterable) : Filterable {
        val conditions: List<Condition> = src.conditions
            .map { SimpleCondition.of(it.target, it.values, QueryOptions.valueOf(it.queryOption.uppercase(Locale.getDefault()))) }

        val conditionOption: ConditionOption = ConditionOption.valueOf(src.conditionOption.uppercase(Locale.getDefault()))

        return SimpleFilterable.of(conditions, conditionOption)
    }
}