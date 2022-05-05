package com.kian.yun.sheetshow.sheet.rest.handler

import com.kian.yun.sheetshow.sheet.rest.code.Response
import com.kian.yun.sheetshow.sheet.common.exception.SheetException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ControllerExceptionHandler {
    @ExceptionHandler(value = [SheetException::class])
    fun sheetShowException(e: SheetException) : Response<String>
    = Response.ofError(e)
}