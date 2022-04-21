package com.kian.yun.sheetshow.filterable.queryOptions

import com.querydsl.core.types.Path
import com.querydsl.core.types.dsl.BooleanExpression

interface QueryOptionProvider {
    fun query(values: List<String>, path: Path<out Any>, queryOptions: QueryOptions): BooleanExpression
}