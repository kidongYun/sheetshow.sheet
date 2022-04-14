package com.kian.yun.sheetshow.sheet.repository

import com.kian.yun.sheetshow.sheet.domain.entity.Fingering
import org.springframework.data.jpa.repository.JpaRepository

interface FingeringRepository : JpaRepository<Fingering, Long>, FingeringRepositoryCustom {
}