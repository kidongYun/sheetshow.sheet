package com.kian.yun.sheetshow.sheet.domain.data.barEl

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class DefaultBarElParserTest : BehaviorSpec() {
    private val defaultBarElParser : DefaultBarElParser = DefaultBarElParser()

    init {
        this.given("parse()") {
            val chevron = "<><><><><>"
            `when`("chevron 괄호가 5개 있는 EL 을 파싱할 때") {
                val result = defaultBarElParser.parse(chevron)

                then("5개의 bar 가 생성되어야 한다") {
                    result.size shouldBe 5
                }
            }
            
            val chevronAndLyrics = "<E>가나<Em>다라마 바사<A>아자차카 <G>타파하<C>"
            `when`("chevron 괄호에 5개 뒤에 각각 가사들이 들어가 있을 때") {
                val result = defaultBarElParser.parse(chevronAndLyrics)

                then("각 가사들이 각 bar 에 순서에 맞게 들어가야 한다") {
                    result.size shouldBe 5
                    result[0].lyrics shouldBe "가나"
                    result[1].lyrics shouldBe "다라마 바사"
                    result[2].lyrics shouldBe "아자차카 "
                    result[3].lyrics shouldBe "타파하"
                    result[4].lyrics shouldBe ""
                }

                then("마디번호 'no' 는 구분이 없음으로 모두 1 이 되어야 한다") {
                    result.forEach { it.no shouldBe 1 }
                }
            }

            val verticalLine = "|<><>|<>|<>|"
            `when`("vertical line 이 5개가 있는 EL 을 파싱할 때") {
                val result = defaultBarElParser.parse(verticalLine)

                then("vertical line 순서대로 마디번호 값이 매겨져야 한다") {
                    result[0].no shouldBe 1
                    result[1].no shouldBe 1
                    result[2].no shouldBe 2
                    result[3].no shouldBe 3
                }
            }

            val songLyrics = "|<E>바람에 날려 꽃이|<G>지는 계절엔|<Dm>아직도 너의 손을|<G>잡은 듯 그런 듯 해.|<C>그때는 아직 꽃이|<G>아름다운 걸|<Dm>지금처럼 사무치게|<G>알지 못했어.|<F>우~<G>너의|<Am>향기가<D>|<F>바람에 <G>실려 오네.|<Am>|<F>우~<G>영원할|<Am>줄 알았<D>던|<F>스물다섯, 스<G>물하|<C>나."
            `when`("bar expression language 를 변환할 때") {
                val result = defaultBarElParser.parse(songLyrics)

                then("bar 개수는 코드의 개수만큼 생겨야 한다") {
                    result.size shouldBe 22
                }
            }
        }
    }
}
