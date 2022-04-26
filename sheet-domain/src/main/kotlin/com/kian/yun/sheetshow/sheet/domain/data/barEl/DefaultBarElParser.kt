package com.kian.yun.sheetshow.sheet.domain.data.barEl

import org.springframework.stereotype.Component

@Component
class DefaultBarElParser : BarElParser {
    override fun parseLyrics(barEl: String): List<String>
    = barEl.split(findLyricsRegex())
        .map { it.filterNot { ch -> ch.toString() == "|" } }
        .drop(1)

    override fun parseChords(barEl: String): List<String>
    = barEl.split(findChordRegex())
        .filter { it.isNotBlank() }
        .map { it.substring(1, it.length - 1) }

    override fun parseNo(barEl: String): List<Long> {
        val numOfVerticalLines : List<Long> = barEl.split(findNoRegex())
            .drop(1)
            .map { it.count { ch -> ch.toString() == "|" } }
            .map { it.toLong() }

        return numOfVerticalLines.mapIndexed { index, _ ->
            numOfVerticalLines.subList(0, index).sumOf { it }
        }.map { (it + 1) }
    }

    private fun findLyricsRegex() : Regex
    = "(<([a-zA-Z]+)>)".toRegex()

    private fun findChordRegex() : Regex
    = "[^(<[a-zA-Z]+)>]".toRegex()

    private fun findNoRegex() : Regex
    = "(<([a-zA-Z]+)>)".toRegex()
}