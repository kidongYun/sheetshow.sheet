package com.kian.yun.sheetshow.sheet.domain.service

import com.kian.yun.sheetshow.sheet.domain.entity.Bar
import com.kian.yun.sheetshow.sheet.domain.repository.support.Condition
import com.kian.yun.sheetshow.sheet.domain.repository.support.Filterable
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
interface BarService {
    fun save(request: Bar) : Long

    fun saveByEl(sheetId: Long, barEl: String) : Long

    fun findById(id: Long) : Bar

    fun find(pageable: Pageable) : List<Bar>

    fun update(request: Bar) : Bar

    fun updateByEl(sheetId: Long, barEl: String) : Long

    fun delete(id: Long)

    fun deleteAllBySheetId(sheetId: Long)

    fun query(filterable: Filterable, pageable: Pageable) : List<Bar>

    fun query(condition: Condition, pageable: Pageable) : List<Bar>

    fun query(filterable: Filterable) : List<Bar>

    fun query(condition: Condition) : List<Bar>

    fun parse(sheetId: Long, barEl: String) : List<Bar>
}