package com.kian.yun.sheetshow.sheet.domain.data.barEl

import com.kian.yun.sheetshow.filterable.util.logger
import com.kian.yun.sheetshow.sheet.common.exception.SheetException
import com.kian.yun.sheetshow.sheet.domain.dto.BarDto
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe

class DefaultBarElParserTest : BehaviorSpec() {
    private val defaultBarElParser : DefaultBarElParser = DefaultBarElParser()

    init {
        given("|<>Heart <>beats fast Colors and promises") {
            val barEl = "|<>Heart <>beats fast Colors and promises"

            `when`("parse()") {
                val result : List<BarDto.Parser> = defaultBarElParser.parse(barEl)

                then("then") {
                    result.size shouldBe 2
                    result.forEachIndexed { index, it ->
                        it.no shouldBe "1"
                        it.lyrics shouldBe listOf("Heart ", "beats fast Colors and promises")[index]
                        it.chord shouldBe listOf("", "")[index]
                    }
                }
            }
        }

        given("|<>Hearbeats fast promises How brave Time") {
            val barEl = "|<>Hearbeats fast promises How brave Time"

            `when`("parse()") {
                val result : List<BarDto.Parser> = defaultBarElParser.parse(barEl)

                then("then") {
                    result.size shouldBe 1
                    result[0].no shouldBe "1"
                    result[0].lyrics shouldBe "Hearbeats fast promises How brave Time"
                    result[0].chord shouldBe ""
                }
            }
        }

        given("|<>Hearbeats <C>fast promises How brave Time") {
            val barEl = "|<>Hearbeats <C>fast promises How brave Time"

            `when`("parse()") {
                val result : List<BarDto.Parser> = defaultBarElParser.parse(barEl)

                then("then") {
                    result.size shouldBe 2
                    result.forEachIndexed { index, it ->
                        it.no shouldBe listOf("1", "1")[index]
                        it.lyrics shouldBe listOf("Hearbeats ", "fast promises How brave Time")[index]
                        it.chord shouldBe listOf("", "C")[index]
                    }
                }
            }
        }

        given("|Hearbeats fast promises How brave Time") {
            val barEl = "|Hearbeats fast promises How brave Time"

            `when`("parse()") {
                val result : List<BarDto.Parser> = defaultBarElParser.parse(barEl)

                then("then") {
                    result.size shouldBe 1
                    result.forEach {
                        it.no shouldBe "1"
                        it.lyrics shouldBe "Hearbeats fast promises How brave Time"
                        it.chord shouldBe ""
                    }
                }
            }
        }

        given("|Hearbeats fast |<C>promises How brave Time") {
            val barEl = "|Hearbeats fast |<C>promises How brave Time"

            `when`("parse()") {
                val result : List<BarDto.Parser> = defaultBarElParser.parse(barEl)

                then("then") {
                    result.size shouldBe 2
                    result.forEachIndexed { index, it ->
                        it.no shouldBe listOf("1", "2")[index]
                        it.lyrics shouldBe listOf("Hearbeats fast ", "promises How brave Time")[index]
                        it.chord shouldBe listOf("", "C")[index]
                    }
                }
            }
        }
        given("|<E>Hearbeats fast <F>promises How brave Time") {
            val barEl = "|<E>Hearbeats fast <F>promises How brave Time"

            `when`("parse()") {
                val result : List<BarDto.Parser> = defaultBarElParser.parse(barEl)

                then("??? ????????? ?????? ????????? ???????????? ???????????? ????????? ??????") {
                    result.size shouldBe 2
                    result.forEach {
                        it.no shouldBe "1"
                        listOf("E", "F") shouldContain it.chord
                    }
                }
            }
        }

        given("<Eb>Hearbeats fast<Bb/D>promises How<Gm>brave<F/A><Bb/D>Time") {
            val barEl = "<Eb>Hearbeats fast<Bb/D>promises How<Gm>brave<F/A><Bb/D>Time"

            `when`("parse()") {
                shouldThrowExactly<SheetException> {
                    defaultBarElParser.parse(barEl)
                }
            }
        }

        given("|<E>??????<Em/G>????????? ??????|<A>???????????? |<G#7/B#>?????????|<C><D>??????") {
            val barEl = "|<E>??????<Em/G>????????? ??????|<A>???????????? |<G#7/B#>?????????|<C><D>??????"

            `when`("parse()") {
                val result = defaultBarElParser.parse(barEl)

                then("??? ???????????? ??? bar ??? ????????? ?????? ???????????? ??????") {
                    result.size shouldBe 6
                    result.forEachIndexed { index, it ->
                        it.no shouldBe listOf("1", "1", "2", "3", "4", "4")[index]
                        it.lyrics shouldBe listOf("??????", "????????? ??????", "???????????? ", "?????????", "", "??????")[index]
                        it.chord shouldBe listOf("E", "Em/G", "A", "G#7/B#", "C", "D")[index]
                    }
                }
            }
        }

        given("|<E>??????|<Em>????????? ??????|<A>???????????? |<G>?????????<C>") {
            val barEl = "|<E>??????|<Em>????????? ??????|<A>???????????? |<G>?????????<C>"

            `when`("parse()") {
                val result = defaultBarElParser.parse(barEl)

                then("??? ???????????? ??? bar ??? ????????? ?????? ???????????? ??????") {
                    result.size shouldBe 5
                    result.forEachIndexed { index, it ->
                        it.no shouldBe listOf("1", "2", "3", "4", "4")[index]
                        it.lyrics shouldBe listOf("??????", "????????? ??????", "???????????? ", "?????????", "")[index]
                        it.chord shouldBe listOf("E", "Em", "A", "G", "C")[index]
                    }
                }
            }
        }

        given("???????????? ???????????? ??????") {
            val barEl = "|<E>????????? ?????? ??????|<G>?????? ?????????|<Dm>????????? ?????? ??????|<G>?????? ??? ?????? ??? ???.|<C>????????? ?????? ??????|<G>???????????? ???|<Dm>???????????? ????????????|<G>?????? ?????????.|<F>???~<G>??????|<Am>?????????<D>|<F>????????? <G>?????? ??????.|<Am>|<F>???~<G>?????????|<Am>??? ??????<D>???|<F>????????????, ???<G>??????|<C>???."
            `when`("parse()") {
                val result = defaultBarElParser.parse(barEl)

                then("bar ????????? ????????? ???????????? ????????? ??????") {
                    result.size shouldBe 22
                    result.forEachIndexed { index, it ->

                        it.no shouldBe listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "9", "10", "10", "11", "11", "12", "13", "13", "14", "14", "15", "15", "16")[index]
                        it.lyrics shouldBe listOf("????????? ?????? ??????", "?????? ?????????", "????????? ?????? ??????", "?????? ??? ?????? ??? ???.", "????????? ?????? ??????", "???????????? ???", "???????????? ????????????", "?????? ?????????.", "???~", "??????", "?????????", "", "????????? ", "?????? ??????.", "", "???~", "?????????", "??? ??????", "???", "????????????, ???", "??????", "???.")[index]
                        it.chord shouldBe listOf("E", "G", "Dm", "G", "C", "G", "Dm", "G", "F", "G", "Am", "D", "F", "G", "Am", "F", "G", "Am", "D", "F", "G", "C")[index]
                    }
                }
            }
        }
    }
}
