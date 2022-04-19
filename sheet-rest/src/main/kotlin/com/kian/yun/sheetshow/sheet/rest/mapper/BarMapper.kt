package com.kian.yun.sheetshow.sheet.rest.mapper

import com.kian.yun.sheetshow.sheet.common.code.SheetCode
import com.kian.yun.sheetshow.sheet.common.exception.SheetException
import com.kian.yun.sheetshow.sheet.common.util.toLong
import com.kian.yun.sheetshow.sheet.domain.entity.Bar
import com.kian.yun.sheetshow.sheet.rest.dto.BarDto
import org.springframework.stereotype.Component

@Component
class BarMapper {
    fun ofEntity(src: BarDto.ReqPost) : Bar
            = Bar(null, toLong(src.no), src.lyrics, toLong(src.fingeringId), toLong(src.sheetId))

    fun ofEntity(id: String, src: BarDto.ReqPut) : Bar
            = Bar(toLong(id), toLong(src.no), src.lyrics, toLong(src.fingeringId), toLong(src.sheetId))

    fun ofRes(src: Bar) : BarDto.Res {
        val id: Long = src.id ?: throw SheetException(SheetCode.DATA_IS_NOT_FOUND)
        return BarDto.Res(id.toString(), src.no.toString(), src.lyrics, src.fingeringId.toString(), src.sheetId.toString())
    }
}