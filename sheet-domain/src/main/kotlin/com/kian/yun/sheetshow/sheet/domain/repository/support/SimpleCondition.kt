package com.kian.yun.sheetshow.sheet.domain.repository.support

import com.kian.yun.sheetshow.filterable.queryOptions.QueryOptions

class SimpleCondition(
    private val target: String,
    private val values: List<String>,
    private val queryOptions: QueryOptions
): Condition {
    companion object {
        fun of(target: String, values: List<String>, queryOptions: QueryOptions)
        = SimpleCondition(target, values, queryOptions)
    }

    override fun getTarget(): String
    = this.target

    override fun getValues(): List<String>
    = this.values

    override fun getQueryOptions(): QueryOptions
    = this.queryOptions
}