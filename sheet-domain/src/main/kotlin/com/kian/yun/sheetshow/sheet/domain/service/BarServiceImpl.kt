package com.kian.yun.sheetshow.sheet.domain.service

import com.kian.yun.sheetshow.sheet.common.code.SheetCode
import com.kian.yun.sheetshow.sheet.common.exception.SheetException
import com.kian.yun.sheetshow.sheet.domain.data.barEl.BarElParser
import com.kian.yun.sheetshow.sheet.domain.entity.Bar
import com.kian.yun.sheetshow.sheet.domain.entity.QBar
import com.kian.yun.sheetshow.sheet.domain.repository.BarRepository
import com.kian.yun.sheetshow.sheet.domain.repository.support.Condition
import com.kian.yun.sheetshow.sheet.domain.repository.support.Filterable
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class BarServiceImpl(
    private val barRepository: BarRepository,
    private val barElParser: BarElParser
) : BarService {

    override fun save(request: Bar): Long
    = barRepository.save(request).id ?: throw SheetException(SheetCode.DATA_IS_NOT_FOUND)

    override fun findById(id: Long): Bar
    = barRepository.findByIdOrNull(id) ?: throw SheetException(SheetCode.DATA_IS_NOT_FOUND)

    override fun find(pageable: Pageable): List<Bar>
    = barRepository.findAll(pageable).toList()

    override fun update(request: Bar): Bar {
        barRepository.save(request)
        return this.findById(request.id ?: throw SheetException(SheetCode.DATA_IS_NOT_FOUND, "request.id is null"))
    }

    override fun delete(id: Long) {
        barRepository.delete(this.findById(id))
    }

    override fun query(filterable: Filterable, pageable: Pageable): List<Bar>
    = barRepository.findByFilterable(filterable, pageable, QBar.bar)

    override fun query(condition: Condition, pageable: Pageable): List<Bar>
    = barRepository.findByCondition(condition, pageable, QBar.bar)

    override fun query(filterable: Filterable): List<Bar>
    = barRepository.findByFilterable(filterable, PageRequest.of(0, 10), QBar.bar)

    override fun query(condition: Condition): List<Bar>
    = barRepository.findByCondition(condition, PageRequest.of(0, 10), QBar.bar)

    override fun parse(barEl: String): List<Bar>
    = barElParser.parse(barEl)
}