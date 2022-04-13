package com.kian.yun.sheetshow.sheet.domain.service

import com.kian.yun.sheetshow.sheet.domain.entity.Bar
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
interface BarService {
    fun save(request: Bar) : Long

    fun findById(id: Long) : Bar

    fun find(pageable: Pageable) : List<Bar>

    fun update(request: Bar) : Bar

    fun delete(id: Long)
}