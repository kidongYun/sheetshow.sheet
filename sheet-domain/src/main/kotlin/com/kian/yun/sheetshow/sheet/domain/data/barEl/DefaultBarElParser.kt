package com.kian.yun.sheetshow.sheet.domain.data.barEl

import org.springframework.stereotype.Component

@Component
class DefaultBarElParser : BarElParser {
    override fun parseLyrics(barEl: String): List<String>
    = barEl.split(chordRegex())
        .map { it.filterNot { ch -> ch.toString() == "|" } }
        .drop(1)

    override fun parseChords(barEl: String): List<String>
    = chordRegex().findAll(barEl).map { it.value.substring(1, it.value.length-1) }.toList()

    override fun parseNo(barEl: String): List<Long> {
        val numOfVerticalLines : List<Long> = barEl.split(chordRegex())
            .drop(1)
            .map { it.count { ch -> ch.toString() == "|" } }
            .map { it.toLong() }

        return numOfVerticalLines.mapIndexed { index, _ ->
            numOfVerticalLines.subList(0, index).sumOf { it }
        }.map { (it + 1) }
    }

    private fun chordRegex() : Regex
    = "<[a-zA-Z0-9#/]+>".toRegex()
}