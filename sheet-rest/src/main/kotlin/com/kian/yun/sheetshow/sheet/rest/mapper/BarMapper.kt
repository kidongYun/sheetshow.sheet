package com.kian.yun.sheetshow.sheet.rest.mapper

import com.kian.yun.sheetshow.sheet.common.code.SheetCode
import com.kian.yun.sheetshow.sheet.common.exception.SheetException
import com.kian.yun.sheetshow.sheet.common.util.toLong
import com.kian.yun.sheetshow.sheet.domain.entity.Bar
import com.kian.yun.sheetshow.sheet.domain.entity.Fingering
import com.kian.yun.sheetshow.sheet.rest.dto.BarPayload
import org.springframework.stereotype.Component

@Component
class BarMapper {
    fun ofEntity(src: BarPayload.ReqPost) : Bar
    = Bar(null, toLong(src.no), src.lyrics, toLong(src.fingeringId), toLong(src.sheetId))

    fun ofEntity(id: String, src: BarPayload.ReqPut) : Bar
    = Bar(toLong(id), toLong(src.no), src.lyrics, toLong(src.fingeringId), toLong(src.sheetId))

    fun ofRes(src: Bar) : BarPayload.Res {
        val id: Long = src.id ?: throw SheetException(SheetCode.DATA_IS_NOT_FOUND)
        return BarPayload.Res(id.toString(), src.no.toString(), src.lyrics, src.fingeringId.toString(), src.sheetId.toString())
    }

    fun ofElRes(bar: Bar, fingering: Fingering) : BarPayload.El.Res {
        return BarPayload.El.Res(
            bar.no.toString(),
            bar.lyrics,
            BarPayload.El.Fingering(
                fingering.type,
                fingering.chord,
                fingering.notes.map {
                    BarPayload.El.Note(it.line.toString(), it.space.toString())
                }
            )
        )
    }
}