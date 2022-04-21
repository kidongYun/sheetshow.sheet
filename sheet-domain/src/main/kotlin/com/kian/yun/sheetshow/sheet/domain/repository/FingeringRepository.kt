package com.kian.yun.sheetshow.sheet.domain.repository

import com.kian.yun.sheetshow.sheet.domain.entity.Fingering
import com.kian.yun.sheetshow.sheet.domain.repository.support.FilterableRepositoryCustom
import org.springframework.data.jpa.repository.JpaRepository

interface FingeringRepository : JpaRepository<Fingering, Long>, FingeringRepositoryCustom, FilterableRepositoryCustom