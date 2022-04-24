package com.kian.yun.sheetshow.sheet.rest.mapper

import com.kian.yun.sheetshow.sheet.common.code.SheetCode
import com.kian.yun.sheetshow.sheet.common.exception.SheetException
import com.kian.yun.sheetshow.sheet.common.util.toLong
import com.kian.yun.sheetshow.sheet.domain.data.Note
import com.kian.yun.sheetshow.sheet.domain.entity.Fingering
import com.kian.yun.sheetshow.sheet.rest.dto.FingeringDto
import org.springframework.stereotype.Component

@Component
class FingeringMapper {
    fun ofEntity(src: FingeringDto.ReqPost) : Fingering
            = Fingering(null, src.type, src.chord, src.notes.map { Note(toLong(it.line), toLong(it.space)) })

    fun ofEntity(id: String, src: FingeringDto.ReqPut) : Fingering
            = Fingering(toLong(id), src.type, src.chord, src.notes.map { Note(toLong(it.line), toLong(it.space)) })

    fun ofRes(src: Fingering) : FingeringDto.Res {
        val id: Long = src.id ?: throw SheetException(SheetCode.DATA_IS_NOT_FOUND)
        return FingeringDto.Res(id.toString(), src.type, src.chord, src.notes.map { FingeringDto.Note(it.line.toString(), it.space.toString()) })
    }
}