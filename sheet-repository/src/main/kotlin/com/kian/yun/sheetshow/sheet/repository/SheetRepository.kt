package com.kian.yun.sheetshow.sheet.repository

import com.kian.yun.sheetshow.sheet.domain.entity.Sheet
import org.springframework.data.jpa.repository.JpaRepository

interface SheetRepository : JpaRepository<Sheet, Long>, SheetRepositoryCustom {
}