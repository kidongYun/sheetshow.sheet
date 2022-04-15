package com.kian.yun.sheetshow.sheet.domain.service

import com.kian.yun.sheetshow.sheet.common.code.SheetCode
import com.kian.yun.sheetshow.sheet.common.exception.SheetException
import com.kian.yun.sheetshow.sheet.domain.entity.Bar
import com.kian.yun.sheetshow.sheet.domain.repository.BarRepository
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class BarServiceImpl(
    private val barRepository: BarRepository
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
}