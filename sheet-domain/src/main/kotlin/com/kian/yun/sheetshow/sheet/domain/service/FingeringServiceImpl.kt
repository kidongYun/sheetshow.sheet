package com.kian.yun.sheetshow.sheet.domain.service

import com.kian.yun.sheetshow.sheet.common.code.SheetCode
import com.kian.yun.sheetshow.sheet.common.exception.SheetException
import com.kian.yun.sheetshow.sheet.domain.entity.Fingering
import com.kian.yun.sheetshow.sheet.domain.repository.FingeringRepository
import com.kian.yun.sheetshow.sheet.domain.repository.FingeringRepositoryAdapter
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class FingeringServiceImpl(
    private val fingeringRepository: FingeringRepository,
    private val fingeringRepositoryAdapter: FingeringRepositoryAdapter
) : FingeringService {

    override fun save(request: Fingering): Long
    = fingeringRepository.save(request).id ?: throw SheetException(SheetCode.DATA_IS_NOT_FOUND)

    override fun findById(id: Long): Fingering
    = fingeringRepository.findByIdOrNull(id) ?: throw SheetException(SheetCode.DATA_IS_NOT_FOUND, String.format("fingering of id '%s' is not found", id))

    override fun find(pageable: Pageable): List<Fingering>
    = fingeringRepository.findAll().toList()

    override fun findByChord(chord: String): List<Fingering>
    = fingeringRepositoryAdapter.findAllByChord(chord)

    override fun update(request: Fingering): Fingering {
        fingeringRepository.save(request)
        return this.findById(request.id ?: throw SheetException(SheetCode.DATA_IS_NOT_FOUND, "request.id is null"))
    }

    override fun delete(id: Long) {
        fingeringRepository.delete(this.findById(id))
    }
}