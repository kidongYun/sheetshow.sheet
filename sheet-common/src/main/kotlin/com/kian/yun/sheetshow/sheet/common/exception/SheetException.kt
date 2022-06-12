package com.kian.yun.sheetshow.sheet.common.exception

import com.kian.yun.sheetshow.sheet.common.code.SheetCode

data class SheetException(
    val code: SheetCode,
    override val message: String? = null
) : RuntimeException()