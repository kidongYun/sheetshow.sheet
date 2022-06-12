package com.kian.yun.sheetshow.sheet.domain.data.barEl

import com.kian.yun.sheetshow.sheet.domain.dto.BarDto
import com.kian.yun.sheetshow.sheet.domain.entity.Bar
import org.springframework.stereotype.Component

@Component
interface BarElParser {
    fun parse(barEl: String) : List<BarDto.Parser>
}