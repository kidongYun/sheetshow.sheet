package com.kian.yun.sheetshow.sheet.rest.mapper

import com.kian.yun.sheetshow.sheet.common.code.SheetCode
import com.kian.yun.sheetshow.sheet.common.exception.SheetException
import com.kian.yun.sheetshow.sheet.common.util.toLong
import com.kian.yun.sheetshow.sheet.domain.entity.Bar
import com.kian.yun.sheetshow.sheet.domain.entity.Fingering
import com.kian.yun.sheetshow.sheet.domain.entity.Sheet
import com.kian.yun.sheetshow.sheet.rest.dto.SheetPayload
import org.springframework.stereotype.Component

@Component
class SheetMapper {
    fun ofEntity(src: SheetPayload.ReqPost) : Sheet
    = Sheet(null, src.name, src.author, src.creatorEmail)

    fun ofEntity(src: SheetPayload.Detail.ReqPost) : Sheet
    = Sheet(null, src.name, src.author, src.creatorEmail)

    fun ofEntity(id: String, src: SheetPayload.ReqPut) : Sheet
    = Sheet(toLong(id), src.name, src.author, src.creatorEmail)

    fun ofEntity(id: String, src: SheetPayload.Detail.ReqPut) : Sheet
    = Sheet(toLong(id), src.name, src.author, src.creatorEmail)

    fun ofRes(src: Sheet) : SheetPayload.Res {
        val id: Long = src.id ?: throw SheetException(SheetCode.DATA_IS_NOT_FOUND)
        return SheetPayload.Res(id.toString(), src.name, src.author, src.creatorEmail)
    }

    fun ofDetailRes(sheet: Sheet, bars: List<Bar>, fingerings: List<Fingering>) : SheetPayload.Detail.Res {
        val sheetId: Long = sheet.id ?: throw SheetException(SheetCode.DATA_IS_NOT_FOUND)
        return SheetPayload.Detail.Res(
            sheetId.toString(),
            sheet.name,
            sheet.author,
            sheet.creatorEmail,
            bars.mapIndexed { index, bar ->
                SheetPayload.Detail.Bar(
                    bar.no.toString(),
                    bar.lyrics,
                    SheetPayload.Detail.Fingering(
                        fingerings[index].type,
                        fingerings[index].chord,
                        fingerings[index].notes.map { SheetPayload.Detail.Note(it.line.toString(), it.space.toString()) }))
            })
    }
}