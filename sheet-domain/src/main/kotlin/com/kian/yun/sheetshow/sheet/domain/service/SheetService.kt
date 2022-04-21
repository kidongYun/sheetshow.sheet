package com.kian.yun.sheetshow.sheet.domain.service

import com.kian.yun.sheetshow.sheet.domain.entity.Sheet
import com.kian.yun.sheetshow.sheet.domain.repository.support.Filterable
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
interface SheetService {
    fun save(request: Sheet) : Long

    fun findById(id: Long) : Sheet

    fun find() : List<Sheet>

    fun find(pageable: Pageable) : List<Sheet>

    fun update(request: Sheet) : Sheet

    fun delete(id: Long)

    fun query(filterable: Filterable, pageable: Pageable) : List<Sheet>
}