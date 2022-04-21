package com.kian.yun.sheetshow.filterable.pathAdapter

import com.querydsl.core.types.dsl.Expressions
import com.querydsl.core.types.dsl.NumberPath
import com.querydsl.core.types.dsl.StringPath
import java.lang.reflect.Field

enum class PathAdapters: PathAdapter {
    StringPathAdapter {
        override fun create(field: Field): StringPath
        = Expressions.stringPath(field.name)
    },
    NumberPathAdapter {
        override fun create(field: Field): NumberPath<Long>
        = Expressions.numberPath(Long::class.java, field.name)
    };

    companion object {
        fun aliasOf(alias : String) : PathAdapter
        = valueOf(alias + "Adapter")
    }
}