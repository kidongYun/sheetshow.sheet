package com.kian.yun.sheetshow.sheet.domain.data.barEl

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class DefaultBarElParserTest : BehaviorSpec() {
    private val defaultBarElParser : DefaultBarElParser = DefaultBarElParser()

    init {
        given("<E>가나<Em>다라마 바사<A>아자차카 <G>타파하<C>") {
            val barEl = "<E>가나<Em>다라마 바사<A>아자차카 <G>타파하<C>"

            `when`("parseLyrics()") {
                val result = defaultBarElParser.parseLyrics(barEl)

                then("각 가사들이 각 bar 에 순서에 맞게 들어가야 한다") {
                    result.size shouldBe 5
                    listOf("가나", "다라마 바사", "아자차카 ", "타파하", "")
                        .forEachIndexed { index, it ->  it shouldBe result[index] }
                }
            }

            `when`("parseChords()") {
                val result = defaultBarElParser.parseChords(barEl)

                then("각 코드들이 각 bar 에 순서에 맞게 들어가야 한다") {
                    result.size shouldBe 5
                    listOf("E", "Em", "A", "G", "C")
                        .forEachIndexed { index, it -> it shouldBe result[index] }
                }
            }

            `when`("parseNo()") {
                val result = defaultBarElParser.parseNo(barEl)

                then("마디번호 'no' 는 모두 1 이 되어야 한다") {
                    result.forEach { it shouldBe 1L }
                }
            }
        }

        given("|<E>가나|<Em>다라마 바사|<A>아자차카 |<G>타파하<C>") {
            val barEl = "|<E>가나|<Em>다라마 바사|<A>아자차카 |<G>타파하<C>"

            `when`("parseLyrics()") {
                val result = defaultBarElParser.parseLyrics(barEl)

                then("각 가사들이 각 bar 에 순서에 맞게 들어가야 한다") {
                    result.size shouldBe 5
                    listOf("가나", "다라마 바사", "아자차카 ", "타파하", "")
                        .forEachIndexed { index, it ->  it shouldBe result[index] }
                }
            }

            `when`("parseChords()") {
                val result = defaultBarElParser.parseChords(barEl)

                then("각 코드들이 각 bar 에 순서에 맞게 들어가야 한다") {
                    result.size shouldBe 5
                    listOf("E", "Em", "A", "G", "C")
                        .forEachIndexed { index, it -> it shouldBe result[index] }
                }
            }

            `when`("parseNo()") {
                val result = defaultBarElParser.parseNo(barEl)

                then("마디번호 'no' 는 수직선 개수에 맞추어 계산되어야 한다") {
                    result.size shouldBe 5
                    listOf(1L, 2L, 3L, 4L, 4L)
                        .forEachIndexed { index, it -> it shouldBe result[index] }
                }
            }
        }

        given("스물다섯 스물하나 가사") {
            val barEl = "|<E>바람에 날려 꽃이|<G>지는 계절엔|<Dm>아직도 너의 손을|<G>잡은 듯 그런 듯 해.|<C>그때는 아직 꽃이|<G>아름다운 걸|<Dm>지금처럼 사무치게|<G>알지 못했어.|<F>우~<G>너의|<Am>향기가<D>|<F>바람에 <G>실려 오네.|<Am>|<F>우~<G>영원할|<Am>줄 알았<D>던|<F>스물다섯, 스<G>물하|<C>나."
            `when`("parseLyrics()") {
                val result = defaultBarElParser.parseLyrics(barEl)

                then("bar 개수는 코드의 개수만큼 생겨야 한다") {
                    result.size shouldBe 22
                }
            }

            `when`("parseChords()") {
                val result = defaultBarElParser.parseChords(barEl)

                then("각 코드들이 각 bar 에 순서에 맞게 들어가야 한다") {
                    result.size shouldBe 22
                    listOf("E", "G", "Dm", "G", "C", "G", "Dm", "G", "F", "G", "Am", "D", "F", "G", "Am", "F", "G", "Am", "D", "F", "G", "C")
                        .forEachIndexed { index, it ->  it shouldBe result[index] }
                }
            }

            `when`("parseNo()") {
                val result = defaultBarElParser.parseNo(barEl)

                then("마디번호 'no' 는 수직선 개수에 맞추어 계산되어야 한다")  {
                    result.size shouldBe 22
                    listOf(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 9L, 10L, 10L, 11L, 11L, 12L, 13L, 13L, 14L, 14L, 15L, 15L, 16L)
                        .forEachIndexed { index, it -> it shouldBe result[index] }
                }
            }
        }
    }
}
