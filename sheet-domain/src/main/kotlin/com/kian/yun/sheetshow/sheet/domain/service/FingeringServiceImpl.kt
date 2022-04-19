package com.kian.yun.sheetshow.sheet.domain.service

import com.kian.yun.sheetshow.sheet.common.code.SheetCode
import com.kian.yun.sheetshow.sheet.common.exception.SheetException
import com.kian.yun.sheetshow.sheet.domain.entity.Fingering
import com.kian.yun.sheetshow.sheet.domain.entity.QFingering
import com.kian.yun.sheetshow.sheet.domain.repository.FingeringRepository
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class FingeringServiceImpl(
    private val fingeringRepository: FingeringRepository
) : FingeringService {

    override fun save(request: Fingering): Long
            = fingeringRepository.save(request).id ?: throw SheetException(SheetCode.DATA_IS_NOT_FOUND)

    override fun findById(id: Long): Fingering
            = fingeringRepository.findByIdOrNull(id) ?: throw SheetException(SheetCode.DATA_IS_NOT_FOUND)

    override fun find(pageable: Pageable): List<Fingering>
            = fingeringRepository.findAll().toList()

    override fun update(request: Fingering): Fingering {
        fingeringRepository.save(request)
        return this.findById(request.id ?: throw SheetException(SheetCode.DATA_IS_NOT_FOUND, "request.id is null"))
    }

    override fun delete(id: Long) {
        fingeringRepository.delete(this.findById(id))
    }

//    override fun query(filterable: Filterable, pageable: Pageable): List<Fingering>
//    = fingeringRepository.findByFilterable(filterable, pageable, QFingering.fingering)
}