package com.kian.yun.sheetshow.filterable.queryOptions

import com.querydsl.core.types.Path
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.core.types.dsl.Expressions
import com.querydsl.core.types.dsl.NumberPath
import com.querydsl.core.types.dsl.StringPath

enum class QueryOptionProviders(
) : QueryOptionProvider {
    DefaultQueryOptionProvider {
        override fun query(values: List<String>, path: Path<out Any>, queryOptions: QueryOptions): BooleanExpression
        = (path as StringPath).`in`(values)
    },

    StringQueryOptionProvider {
        override fun query(values: List<String>, path: Path<out Any>, queryOptions: QueryOptions): BooleanExpression {
            if(QueryOptions.EQUAL == queryOptions) {
                return this.equal(values, path as StringPath)
            }

            if(QueryOptions.LIKE == queryOptions) {
                return this.like(values, path as StringPath)
            }

            return Expressions.asBoolean(true).isTrue
        }

        private fun equal(values: List<String>, path: Path<out Any>): BooleanExpression
                = (path as StringPath).`in`(values)

        private fun like(values: List<String>, path: Path<out Any>): BooleanExpression
                = values
            .map { value -> (path as StringPath).contains(value) }
            .reduce { acc, boolExp -> acc.or(boolExp) }
    },

    NumberQueryOptionProvider {
        override fun query(values: List<String>, path: Path<out Any>, queryOptions: QueryOptions): BooleanExpression
        = (path as NumberPath<Long>).`in`(values.map { it.toLong() })
    };

    companion object {
        fun aliasOf(alias : String) : QueryOptionProvider
        = valueOf(alias + "QueryOptionProvider")
    }
}