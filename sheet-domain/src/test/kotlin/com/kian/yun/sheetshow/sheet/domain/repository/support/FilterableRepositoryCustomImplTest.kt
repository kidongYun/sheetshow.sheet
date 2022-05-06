package com.kian.yun.sheetshow.sheet.domain.repository.support

import com.kian.yun.sheetshow.filterable.queryOptions.QueryOptions
import com.kian.yun.sheetshow.sheet.domain.repository.helper.Dummy
import com.kian.yun.sheetshow.sheet.domain.repository.helper.DummyRepository
import com.kian.yun.sheetshow.sheet.domain.repository.helper.QDummy
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.inspectors.forAtLeastOne
import io.kotest.matchers.collections.shouldBeIn
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest

@SpringBootTest
class FilterableRepositoryCustomImplTest : BehaviorSpec() {
    override fun extensions() = listOf(SpringExtension)

    @Autowired
    private lateinit var filterableRepositoryCustomImpl: FilterableRepositoryCustomImpl

    @Autowired
    private lateinit var dummyRepository: DummyRepository

    init {
        this.given("findByCondition") {
            dummyRepository.save(Dummy(1, "jack", "red"))
            dummyRepository.save(Dummy(2, "chloe", "blue"))
            dummyRepository.save(Dummy(3, "tommy", "blue"))
            dummyRepository.save(Dummy(4, "chloe", "yellow"))
            dummyRepository.save(Dummy(5, "mike", "light yellow"))
            dummyRepository.save(Dummy(6, "pike", "light red"))

            `when`("condition 이 아무런 조건에 해당하지 않을 때") {
                val result = filterableRepositoryCustomImpl.findByCondition(
                    SimpleCondition.of("", listOf(), QueryOptions.EQUAL), PageRequest.of(0, 10), QDummy.dummy)

                then("모든 Dummy 데이터가 반환되어야 한다") {
                    result.size shouldBe 6
                    result.forEach { it.id shouldBeIn listOf(1, 2, 3, 4, 5, 6) }
                }
            }

            `when`("name 이 'jack' 인 Dummy 데이터를 가져오려고 할 때") {
                val result = filterableRepositoryCustomImpl.findByCondition(SimpleCondition.of("name", listOf("jack"), QueryOptions.EQUAL), PageRequest.of(0, 10), QDummy.dummy)

                then("'jack' 을 이름으로 가진 Dummy 데이터만 반환되어야 한다") {
                    result.size shouldBe 1
                    result.forEach { it.name shouldBe "jack" }
                }
            }

            `when`("color 가 'blue' 인 Dummy 데이터를 가져오려고 할 때") {
                val result = filterableRepositoryCustomImpl.findByCondition(SimpleCondition.of("color", listOf("blue"), QueryOptions.EQUAL), PageRequest.of(0, 10), QDummy.dummy)

                then("'blue' 를 color 값으로 가진 Dummy 데이터만 반환되어야 한다") {
                    result.size shouldBe 2
                    result.forEach { it.color shouldBe "blue" }
                }
            }

            `when`("name 이 'jack', 'chloe' 인 Dummy 데이터를 가져오려고 할 때") {
                val result = filterableRepositoryCustomImpl.findByCondition(SimpleCondition.of("name", listOf("jack", "chloe"), QueryOptions.EQUAL), PageRequest.of(0, 10), QDummy.dummy)

                then("'jack', 'chloe' 를 name 값으로 가진 Dummy 데이터만 반환되어야 한다") {
                    result.size shouldBe 3
                    result.forEach { it.name shouldBeIn listOf("jack", "chloe") }
                }
            }

            `when`("color 에 'yellow' 가 포함된 Dummy 데이터를 가져오려고 할 때") {
                val result = filterableRepositoryCustomImpl.findByCondition(SimpleCondition.of("color", listOf("yellow"), QueryOptions.LIKE), PageRequest.of(0, 10), QDummy.dummy)

                then("'yellow' 를 포함한 값을 color 로 가진 Dummy 데이터만 반환되어야 한다") {
                    result.size shouldBe 2
                    result.forEach { it.color shouldContain "yellow" }
                }
            }

            `when`("color 에 'yellow', 'red' 가 포함된 Dummy 데이터를 가져오려고 할 때") {
                val result = filterableRepositoryCustomImpl.findByCondition(SimpleCondition.of("color", listOf("yellow", "red"), QueryOptions.LIKE), PageRequest.of(0, 10), QDummy.dummy)

                then("'yellow', 'red' 포함한 값을 color 로 가진 데이터만 반환되어야 한다") {
                    result.size shouldBe 4
                    result.forAtLeastOne { it.color shouldContain "yellow" }
                    result.forAtLeastOne { it.color shouldContain "red" }
                }
            }
        }

        this.given("findByFilterable") {
            dummyRepository.save(Dummy(1, "jack", "red"))
            dummyRepository.save(Dummy(2, "chloe", "blue"))
            dummyRepository.save(Dummy(3, "tommy", "blue"))
            dummyRepository.save(Dummy(4, "chloe", "yellow"))
            dummyRepository.save(Dummy(5, "mike", "light yellow"))
            dummyRepository.save(Dummy(6, "pike", "light red"))
            dummyRepository.save(Dummy(7, "tom", "pink"))

            `when`("filterable 이 아무런 조건에 해당하지 않을 때") {
                val result = filterableRepositoryCustomImpl.findByFilterable(SimpleFilterable.of(listOf(
                    SimpleCondition.of("", listOf(), QueryOptions.EQUAL)
                ), ConditionOption.OR), PageRequest.of(0, 10), QDummy.dummy)

                then("모든 Dummy 데이터가 반환되어야 한다")  {
                    result.size shouldBe 7
                    result.forEach { it.id shouldBeIn listOf(1, 2, 3, 4, 5, 6, 7) }
                }
            }

            `when`("name 조건으로 'tommy', color 조건으로 'red' 값이 들어왔을 때") {
                val result = filterableRepositoryCustomImpl.findByFilterable(SimpleFilterable.of(listOf(
                    SimpleCondition.of("name", listOf("tommy"), QueryOptions.EQUAL),
                    SimpleCondition.of("color", listOf("red"), QueryOptions.EQUAL)
                ), ConditionOption.OR), PageRequest.of(0, 10), QDummy.dummy)

                then("name 이 'tommy' 이거나 color 가 'red' 인 Dummy 데이터가 반환되어야 한다") {
                    result.size shouldBe 2
                    result.forAtLeastOne { it.name shouldBe "tommy" }
                    result.forAtLeastOne { it.color shouldBe "red" }
                }
            }

            `when`("name 에 'tom' 이 포함되거나 color 에 'yellow' 가 포함되는 조건이 들어왔을 때") {
                val result = filterableRepositoryCustomImpl.findByFilterable(SimpleFilterable.of(listOf(
                    SimpleCondition.of("name", listOf("tom"), QueryOptions.LIKE),
                    SimpleCondition.of("color", listOf("yellow"), QueryOptions.LIKE)
                ), ConditionOption.OR), PageRequest.of(0, 10), QDummy.dummy)

                then("name 에 'tom' 이 포함되거나 color 에 'yellow'  가 포함되는 Dummy 데이터가 반환되어야 한다") {
                    result.size shouldBe 4
                    result.forAtLeastOne { it.name shouldContain "tom" }
                    result.forAtLeastOne { it.color shouldContain "yellow" }
                }
            }

            `when`("name 조건으로 'chloe', color 조건으로 'blue' 값이 들어왔을 때") {
                val result = filterableRepositoryCustomImpl.findByFilterable(SimpleFilterable.of(listOf(
                    SimpleCondition.of("name", listOf("chloe"), QueryOptions.EQUAL),
                    SimpleCondition.of("color", listOf("blue"), QueryOptions.EQUAL),
                ), ConditionOption.AND), PageRequest.of(0, 10), QDummy.dummy)

                then("name 은 'chloe' 이고 color 는 'blue' 인 Dummy 데이터가 반환되어야 한다") {
                    result.size shouldBe 1
                    result.forEach {
                        it.name shouldBe "chloe"
                        it.color shouldBe "blue"
                    }
                }
            }
        }

        this.given("pageable") {
            dummyRepository.save(Dummy(1, "name", "color"))
            dummyRepository.save(Dummy(2, "name", "color"))
            dummyRepository.save(Dummy(3, "name", "color"))
            dummyRepository.save(Dummy(4, "name", "color"))
            dummyRepository.save(Dummy(5, "name", "color"))
            dummyRepository.save(Dummy(6, "name", "color"))
            dummyRepository.save(Dummy(7, "name", "color"))
            dummyRepository.save(Dummy(8, "name", "color"))
            dummyRepository.save(Dummy(9, "name", "color"))
            dummyRepository.save(Dummy(10, "name", "color"))
            dummyRepository.save(Dummy(11, "name", "color"))
            dummyRepository.save(Dummy(12, "name", "color"))
            dummyRepository.save(Dummy(13, "name", "color"))
            dummyRepository.save(Dummy(14, "name", "color"))
            dummyRepository.save(Dummy(15, "name", "color"))
            dummyRepository.save(Dummy(16, "name", "color"))
            dummyRepository.save(Dummy(17, "name", "color"))
            dummyRepository.save(Dummy(18, "name", "color"))

            `when`("findByCondition 에 page 는 '0' 이고 size 는 '5' 인 pageable 이 주어질 때") {
                val result = filterableRepositoryCustomImpl.findByCondition(SimpleCondition.of("name", listOf("name"), QueryOptions.EQUAL), PageRequest.of(0, 5), QDummy.dummy)

                then("id 1, 2, 3, 4, 5 인 Dummy 데이터가 반환되어야 한다") {
                    result.size shouldBe 5
                    result.forEach { it.id shouldBeIn listOf(1, 2, 3, 4, 5)}
                }
            }

            `when`("findByCondition 에 page 는 '3' 이고 size 는 '5' 인 pageable 이 주어질 때 ") {
                val result = filterableRepositoryCustomImpl.findByCondition(SimpleCondition.of("name", listOf("name"), QueryOptions.EQUAL), PageRequest.of(3, 5), QDummy.dummy)

                then("id 16, 17, 18 인 Dummy 데이터가 반환되어야 한다") {
                    result.size shouldBe 3
                    result.forEach { it.id shouldBeIn listOf(16, 17, 18) }
                }
            }

            `when`("findByFilterable 에 page '0' 이고 size 는 '5' 인 page 가 주어질 때") {
                val result = filterableRepositoryCustomImpl.findByFilterable(SimpleFilterable.of(listOf(
                    SimpleCondition.of("name", listOf("name"), QueryOptions.EQUAL)
                ), ConditionOption.AND), PageRequest.of(0, 5), QDummy.dummy)

                then("id 1, 2, 3, 4, 5 인 Dummy 데이터가 반환되어야 한다") {
                    result.size shouldBe 5
                    result.forEach { it.id shouldBeIn listOf(1, 2, 3, 4, 5) }
                }
            }

            `when`("findByFilterable 에 page 는 '3' 이고 size 는 '5' 인 page 가 주어질 때") {
                val result = filterableRepositoryCustomImpl.findByFilterable(SimpleFilterable.of(listOf(
                    SimpleCondition.of("name", listOf("name"), QueryOptions.EQUAL)
                ), ConditionOption.AND), PageRequest.of(3, 5), QDummy.dummy)

                then("id 16, 17, 18 인 Dummy 데이터가 반환되어야 한다") {
                    result.size shouldBe 3
                    result.forEach { it.id shouldBeIn listOf(16, 17, 18) }
                }
            }
        }
    }
}
