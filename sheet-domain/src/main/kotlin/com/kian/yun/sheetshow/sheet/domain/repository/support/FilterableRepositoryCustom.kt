package com.kian.yun.sheetshow.sheet.domain.repository.support

import com.querydsl.core.types.EntityPath
import org.springframework.data.domain.Pageable

interface FilterableRepositoryCustom {
    fun <T, U : EntityPath<T>> findByCondition(condition: Condition, pageable: Pageable, qClass: U) : List<T>

    fun <T, U : EntityPath<T>> findByFilterable(filterable: Filterable, pageable: Pageable, qClass: U) : List<T>
}