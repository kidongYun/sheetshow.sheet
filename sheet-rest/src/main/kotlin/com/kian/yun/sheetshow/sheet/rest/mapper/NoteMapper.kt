package com.kian.yun.sheetshow.sheet.rest.mapper

import com.kian.yun.sheetshow.sheet.common.code.SheetCode
import com.kian.yun.sheetshow.sheet.common.exception.SheetException
import com.kian.yun.sheetshow.sheet.common.util.toLong
import com.kian.yun.sheetshow.sheet.domain.entity.Note
import com.kian.yun.sheetshow.sheet.rest.dto.NoteDto
import org.springframework.stereotype.Component

@Component
class NoteMapper {
    fun ofEntity(src: NoteDto.ReqPost) : Note
    = Note(null, toLong(src.line), toLong(src.space), toLong(src.fingeringId))

    fun ofEntity(id: String, src: NoteDto.ReqPut) : Note
    = Note(toLong(id), toLong(src.line), toLong(src.space), toLong(src.fingeringId))

    fun ofRes(src: Note) : NoteDto.Res {
        val id:Long = src.id ?: throw SheetException(SheetCode.DATA_IS_NOT_FOUND)
        return NoteDto.Res(id.toString(), src.line.toString(), src.space.toString(), src.fingeringId.toString())
    }
}