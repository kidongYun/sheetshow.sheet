package com.kian.yun.sheetshow.sheet.domain.data.barEl

import com.kian.yun.sheetshow.filterable.util.logger
import com.kian.yun.sheetshow.sheet.domain.entity.Bar
import org.springframework.stereotype.Component

@Component
class DefaultBarElParser : BarElParser {
    override fun parse(barEl: String): List<Bar> {
        val log = logger()

        val lyrics : List<String> = barEl.split(findLyricsRegex())
            .drop(1)

        log.info("lyrics : {}", lyrics)

        var chords : List<String> = barEl.split(findChordRegex())
            .filter { it.isNotBlank() }

        log.info("chords : {}", chords)

        return lyrics.mapIndexed { index, lyric ->
            Bar(null, 1, lyric, 1, 1)
        }
    }

    private fun findLyricsRegex() : Regex
    = "(<([a-zA-Z]+)>)".toRegex()

    private fun findChordRegex() : Regex
    = "[^(<[a-zA-Z]+)>]".toRegex()
}