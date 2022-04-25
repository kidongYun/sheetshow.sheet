package com.kian.yun.sheetshow.sheet.domain.data.barEl

import com.kian.yun.sheetshow.sheet.domain.entity.Bar

interface BarElParser {
    fun parse(barEl : String) : List<Bar>
}