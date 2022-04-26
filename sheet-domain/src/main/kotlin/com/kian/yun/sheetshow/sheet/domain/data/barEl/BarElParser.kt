package com.kian.yun.sheetshow.sheet.domain.data.barEl

import org.springframework.stereotype.Component

@Component
interface BarElParser {
    fun parseLyrics(barEl: String) : List<String>

    fun parseChords(barEl: String) : List<String>

    fun parseNo(barEl: String) : List<Long>
}