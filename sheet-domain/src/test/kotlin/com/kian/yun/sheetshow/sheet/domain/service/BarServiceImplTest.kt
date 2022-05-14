package com.kian.yun.sheetshow.sheet.domain.service

import com.kian.yun.sheetshow.sheet.domain.entity.Bar

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest

@SpringBootTest
class BarServiceImplTest : BehaviorSpec() {
    override fun extensions() = listOf(SpringExtension)

    @Autowired
    private lateinit var barServiceImpl: BarServiceImpl

    init {
        this.given("deleteAllBySheetId") {
            barServiceImpl.save(Bar(null, 1, "비가 오는 거리에는", 1, 1))
            barServiceImpl.save(Bar(null, 1, "내가 할일이 없어서", 2, 2))
            barServiceImpl.save(Bar(null, 2, "그냥 마냥 걷다보면", 3, 2))
            barServiceImpl.save(Bar(null, 3, "눈물 나는 마주치지", 3, 3))

            `when`("when") {
                barServiceImpl.deleteAllBySheetId(2)
                val result = barServiceImpl.find(PageRequest.of(0, 10))

                then("then") {
                    result.size shouldBe 2
                    result.forEach {
                        it.id shouldNotBe 2
                    }
                }
            }
        }
    }
}
