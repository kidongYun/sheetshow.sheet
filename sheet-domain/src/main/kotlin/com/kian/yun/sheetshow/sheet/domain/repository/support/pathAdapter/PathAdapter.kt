package com.kian.yun.sheetshow.filterable.pathAdapter

import com.querydsl.core.types.Path
import java.lang.reflect.Field

interface PathAdapter {
    fun create(field: Field) : Path<out Any>
}