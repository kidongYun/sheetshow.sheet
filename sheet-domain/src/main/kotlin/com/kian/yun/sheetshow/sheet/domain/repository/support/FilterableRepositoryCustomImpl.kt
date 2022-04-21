package com.kian.yun.sheetshow.sheet.domain.repository.support

import com.kian.yun.sheetshow.filterable.pathAdapter.PathAdapters
import com.kian.yun.sheetshow.filterable.queryOptions.QueryOptionProviders
import com.kian.yun.sheetshow.filterable.util.isNotStatic
import com.querydsl.core.types.EntityPath
import com.querydsl.core.types.dsl.*
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

@Repository
class FilterableRepositoryCustomImpl(
    private val queryFactory: JPAQueryFactory
) : FilterableRepositoryCustom {

    override fun <T, U : EntityPath<T>> findByCondition(condition: Condition, pageable: Pageable, qClass: U): List<T>
    = queryFactory.selectFrom(qClass)
            .where(whereQueryOfCondition(condition, qClass))
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .fetch()

    private fun <T, U : EntityPath<T>> whereQueryOfCondition(condition: Condition, qClass: U) : BooleanExpression {
        qClass::class.java.declaredFields
            .filter { isNotStatic(it) }
            .map { PathAdapters.aliasOf(it.type.simpleName).create(it) }
            .filter { it.toString() == condition.getTarget() }
            .forEach {
                return QueryOptionProviders.aliasOf(qClass.type.getDeclaredField(condition.getTarget()).type.simpleName)
                    .query(condition.getValues(), it, condition.getQueryOptions())
            }

        return Expressions.asBoolean(true).isTrue
    }

    override fun <T, U : EntityPath<T>> findByFilterable(filterable: Filterable, pageable: Pageable, qClass: U): List<T>
    = queryFactory.selectFrom(qClass)
        .where(whereQueryOfFilterable(filterable, qClass))
        .offset(pageable.offset)
        .limit(pageable.pageSize.toLong())
        .fetch()

    private fun <T, U : EntityPath<T>> whereQueryOfFilterable(filterable: Filterable, qClass: U) : BooleanExpression
    = filterable.getConditions()
        .map { whereQueryOfCondition(it, qClass) }
        .reduce { acc, boolExp -> filterable.getConditionOption().query.invoke(acc, boolExp) }
}