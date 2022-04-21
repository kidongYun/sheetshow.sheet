package com.kian.yun.sheetshow.sheet.domain.repository.support

import com.kian.yun.sheetshow.filterable.queryOptions.QueryOptions

interface Condition {
    fun getTarget(): String

    fun getValues(): List<String>

    fun getQueryOptions(): QueryOptions
}