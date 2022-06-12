package com.kian.yun.sheetshow.sheet.rest.mapper

import com.kian.yun.sheetshow.sheet.common.code.SheetCode
import com.kian.yun.sheetshow.sheet.common.exception.SheetException
import com.kian.yun.sheetshow.sheet.common.util.toLong
import com.kian.yun.sheetshow.sheet.domain.data.Note
import com.kian.yun.sheetshow.sheet.domain.entity.Fingering
import com.kian.yun.sheetshow.sheet.rest.dto.FingeringPayload
import org.springframework.stereotype.Component

@Component
class FingeringMapper {
    fun ofEntity(src: FingeringPayload.ReqPost) : Fingering
            = Fingering(null, src.type, src.chord, src.notes.map { Note(toLong(it.line), toLong(it.space)) })

    fun ofEntity(id: String, src: FingeringPayload.ReqPut) : Fingering
            = Fingering(toLong(id), src.type, src.chord, src.notes.map { Note(toLong(it.line), toLong(it.space)) })

    fun ofRes(src: Fingering) : FingeringPayload.Res {
        val id: Long = src.id ?: throw SheetException(SheetCode.DATA_IS_NOT_FOUND)
        return FingeringPayload.Res(id.toString(), src.type, src.chord, src.notes.map { FingeringPayload.Note(it.line.toString(), it.space.toString()) })
    }
}