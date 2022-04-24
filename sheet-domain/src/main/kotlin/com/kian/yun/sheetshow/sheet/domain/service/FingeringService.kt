package com.kian.yun.sheetshow.sheet.domain.service

import com.kian.yun.sheetshow.sheet.domain.entity.Fingering
import com.kian.yun.sheetshow.sheet.domain.repository.support.Filterable
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
interface FingeringService {
    fun save(request: Fingering) : Long

    fun findById(id: Long) : Fingering

    fun find(pageable: Pageable) : List<Fingering>

    fun update(request: Fingering) : Fingering

    fun delete(id: Long)
}
