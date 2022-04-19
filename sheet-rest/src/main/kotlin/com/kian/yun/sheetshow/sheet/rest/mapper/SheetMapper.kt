package com.kian.yun.sheetshow.sheet.rest.mapper

import com.kian.yun.sheetshow.sheet.common.code.SheetCode
import com.kian.yun.sheetshow.sheet.common.exception.SheetException
import com.kian.yun.sheetshow.sheet.common.util.toLong
import com.kian.yun.sheetshow.sheet.domain.entity.Sheet
import com.kian.yun.sheetshow.sheet.rest.dto.SheetDto
import org.springframework.stereotype.Component

@Component
class SheetMapper {
    fun ofEntity(src: SheetDto.ReqPost) : Sheet
    = Sheet(null, src.name, src.author, src.creatorEmail)

    fun ofEntity(id: String, src: SheetDto.ReqPut) : Sheet
    = Sheet(toLong(id), src.name, src.author, src.creatorEmail)

    fun ofRes(src: Sheet) : SheetDto.Res {
        val id: Long = src.id ?: throw SheetException(SheetCode.DATA_IS_NOT_FOUND)
        return SheetDto.Res(id.toString(), src.name, src.author, src.creatorEmail)
    }
}