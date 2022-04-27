package com.kian.yun.sheetshow.sheet.domain.service

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.extensions.spring.SpringExtension
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class SheetServiceImplTest : BehaviorSpec() {
    override fun extensions() = listOf(SpringExtension)

    init {
        this.given("given") {
            `when`("when") {
                then("then") {

                }
            }
        }
    }
}
