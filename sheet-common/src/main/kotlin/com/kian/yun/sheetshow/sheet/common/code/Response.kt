package com.kian.yun.sheetshow.sheet.common.code

import com.kian.yun.sheetshow.sheet.common.exception.SheetException

data class Response<T>(
    val code: SheetCode,
    val data: T? = null
) {
    companion object {
        fun ofSuccess(): Response<Void> = Response(SheetCode.SUCCESS)
        fun <T> ofSuccess(data: T): Response<T> = Response(SheetCode.SUCCESS, data)
        fun ofError(e: SheetException): Response<String> = Response(e.code, e.message)
    }
}