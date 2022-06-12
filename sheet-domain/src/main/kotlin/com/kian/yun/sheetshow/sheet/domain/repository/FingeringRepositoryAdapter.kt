package com.kian.yun.sheetshow.sheet.domain.repository

import org.springframework.stereotype.Component

@Component
class FingeringRepositoryAdapter(
    private val fingeringRepository: FingeringRepository
)  {
    fun findAllByChord(chord: String)
    = fingeringRepository.findAll()
        .filter { it.chord == chord }
}