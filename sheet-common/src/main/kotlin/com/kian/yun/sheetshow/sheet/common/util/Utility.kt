package com.kian.yun.sheetshow.sheet.common.util

import com.kian.yun.sheetshow.sheet.common.code.SheetCode
import com.kian.yun.sheetshow.sheet.common.exception.SheetException
import org.slf4j.LoggerFactory
import org.slf4j.Logger

inline fun <reified T> T.logger(): Logger = LoggerFactory.getLogger(T::class.java)

fun toLong(src: String) : Long {
    try {
        return src.toLong()
    } catch (e: NumberFormatException) {
        throw SheetException(SheetCode.NUMBER_FORMAT_PARSE_FAILED)
    }
}