package com.kian.yun.sheetshow.sheet.domain.repository

import com.kian.yun.sheetshow.sheet.domain.entity.Sheet
import com.kian.yun.sheetshow.sheet.domain.repository.support.FilterableRepositoryCustom
import org.springframework.data.jpa.repository.JpaRepository

interface SheetRepository : JpaRepository<Sheet, Long>, SheetRepositoryCustom, FilterableRepositoryCustom