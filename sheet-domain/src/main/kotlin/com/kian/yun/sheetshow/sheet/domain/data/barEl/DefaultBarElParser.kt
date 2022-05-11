package com.kian.yun.sheetshow.sheet.domain.data.barEl

import com.kian.yun.sheetshow.filterable.util.logger
import com.kian.yun.sheetshow.sheet.common.code.SheetCode
import com.kian.yun.sheetshow.sheet.common.exception.SheetException
import com.kian.yun.sheetshow.sheet.domain.dto.BarDto
import org.springframework.stereotype.Component

@Component
class DefaultBarElParser : BarElParser {
    override fun parse(barEl: String): List<BarDto.Parser>
    = parseByBar(barEl).entries
        .also {
            val log = logger()
            log.info("it : $it")
        }
        .associate { it.key to parseByChord(it.value) }
        .also {
            val log = logger()
            log.info("it : $it")
        }.entries
        .map { bar -> bar.value
            .map { chord -> BarDto.Parser(bar.key.toString(), chord.second, chord.first) }
        }.flatten()

    private fun parseByChord(barEl: String) : List<Pair<String, String>> {
        val lyrics : List<String> = barEl.split(chordRegex()).drop(1)
        val chords : List<String> = chordRegex().findAll(barEl).map { it.value.substring(1, it.value.length-1) }.toList()

        val log = logger()
        log.info("lyrics : $lyrics")
        log.info("lyrics.size : '${lyrics.size}'")
        log.info("chords : '$chords'")
        log.info("chords.size : '${chords.size}'")

        if(lyrics.isEmpty() && chords.isEmpty()) {
            return listOf("" to barEl)
        }

        return chords
            .mapIndexed { index, it -> it to lyrics[index] }
            .also {
                val log = logger()
                log.info("it : $it")
            }
    }

    private fun parseByBar(barEl: String) : Map<Long, String> {
        if(!barEl.contains("|")) {
            throw SheetException(SheetCode.VERTICAL_LINE_IN_NOT_EXISTED, "barEl '$barEl' doesn't have '|' letter")
        }
        val splitByBar : List<String> = barEl.split("|")

        val log = logger()
        log.info("splitByBar : $splitByBar")

        return splitByBar
            .subList(1, splitByBar.size)
            .mapIndexed { index, it -> (index + 1).toLong() to it }
            .toMap()
    }

    private fun chordRegex() : Regex
    = "<[a-zA-Z0-9#/]+>|<>".toRegex()
}