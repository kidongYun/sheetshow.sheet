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

                then("한 마디에 코드 두개로 이루어진 결과물이 나와야 한다") {
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

        given("|<E>가나<Em/G>다라마 바사|<A>아자차카 |<G#7/B#>타파하|<C><D>감차") {
            val barEl = "|<E>가나<Em/G>다라마 바사|<A>아자차카 |<G#7/B#>타파하|<C><D>감차"

            `when`("parse()") {
                val result = defaultBarElParser.parse(barEl)

                then("각 가사들이 각 bar 에 순서에 맞게 들어가야 한다") {
                    result.size shouldBe 6
                    result.forEachIndexed { index, it ->
                        it.no shouldBe listOf("1", "1", "2", "3", "4", "4")[index]
                        it.lyrics shouldBe listOf("가나", "다라마 바사", "아자차카 ", "타파하", "", "감차")[index]
                        it.chord shouldBe listOf("E", "Em/G", "A", "G#7/B#", "C", "D")[index]
                    }
                }
            }
        }

        given("|<E>가나|<Em>다라마 바사|<A>아자차카 |<G>타파하<C>") {
            val barEl = "|<E>가나|<Em>다라마 바사|<A>아자차카 |<G>타파하<C>"

            `when`("parse()") {
                val result = defaultBarElParser.parse(barEl)

                then("각 가사들이 각 bar 에 순서에 맞게 들어가야 한다") {
                    result.size shouldBe 5
                    result.forEachIndexed { index, it ->
                        it.no shouldBe listOf("1", "2", "3", "4", "4")[index]
                        it.lyrics shouldBe listOf("가나", "다라마 바사", "아자차카 ", "타파하", "")[index]
                        it.chord shouldBe listOf("E", "Em", "A", "G", "C")[index]
                    }
                }
            }
        }

        given("스물다섯 스물하나 가사") {
            val barEl = "|<E>바람에 날려 꽃이|<G>지는 계절엔|<Dm>아직도 너의 손을|<G>잡은 듯 그런 듯 해.|<C>그때는 아직 꽃이|<G>아름다운 걸|<Dm>지금처럼 사무치게|<G>알지 못했어.|<F>우~<G>너의|<Am>향기가<D>|<F>바람에 <G>실려 오네.|<Am>|<F>우~<G>영원할|<Am>줄 알았<D>던|<F>스물다섯, 스<G>물하|<C>나."
            `when`("parse()") {
                val result = defaultBarElParser.parse(barEl)

                then("bar 개수는 코드의 개수만큼 생겨야 한다") {
                    result.size shouldBe 22
                    result.forEachIndexed { index, it ->

                        it.no shouldBe listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "9", "10", "10", "11", "11", "12", "13", "13", "14", "14", "15", "15", "16")[index]
                        it.lyrics shouldBe listOf("바람에 날려 꽃이", "지는 계절엔", "아직도 너의 손을", "잡은 듯 그런 듯 해.", "그때는 아직 꽃이", "아름다운 걸", "지금처럼 사무치게", "알지 못했어.", "우~", "너의", "향기가", "", "바람에 ", "실려 오네.", "", "우~", "영원할", "줄 알았", "던", "스물다섯, 스", "물하", "나.")[index]
                        it.chord shouldBe listOf("E", "G", "Dm", "G", "C", "G", "Dm", "G", "F", "G", "Am", "D", "F", "G", "Am", "F", "G", "Am", "D", "F", "G", "C")[index]
                    }
                }
            }
        }
    }
}
