package com.kian.yun.sheetshow.sheet.domain.service

import com.kian.yun.sheetshow.sheet.common.code.SheetCode
import com.kian.yun.sheetshow.sheet.common.exception.SheetException
import com.kian.yun.sheetshow.sheet.domain.entity.QSheet
import com.kian.yun.sheetshow.sheet.domain.entity.Sheet
import com.kian.yun.sheetshow.sheet.domain.repository.SheetRepository
import com.kian.yun.sheetshow.sheet.domain.repository.support.Filterable
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class SheetServiceImpl(
    private val sheetRepository: SheetRepository
) : SheetService {
    override fun save(request: Sheet): Long
    = sheetRepository.save(request).id ?: throw SheetException(SheetCode.DATA_IS_NOT_FOUND)

    override fun findById(id: Long): Sheet
    = sheetRepository.findByIdOrNull(id) ?: throw SheetException(SheetCode.DATA_IS_NOT_FOUND)

    override fun find(): List<Sheet>
    = sheetRepository.findAll()

    override fun find(pageable: Pageable): List<Sheet>
    = sheetRepository.findAll(pageable).toList()

    override fun update(request: Sheet): Sheet {
        sheetRepository.save(request)
        return this.findById(request.id ?: throw SheetException(SheetCode.DATA_IS_NOT_FOUND, "request.id is null"))
    }

    override fun delete(id: Long) {
        sheetRepository.delete(this.findById(id))
    }

    override fun query(filterable: Filterable, pageable: Pageable): List<Sheet>
    = sheetRepository.findByFilterable(filterable, pageable, QSheet.sheet)
}