package com.kian.yun.sheetshow.sheet.domain.repository

import com.kian.yun.sheetshow.sheet.domain.entity.Bar
import com.kian.yun.sheetshow.sheet.domain.repository.support.FilterableRepositoryCustom
import org.springframework.data.jpa.repository.JpaRepository

interface BarRepository : JpaRepository<Bar, Long>, BarRepositoryCustom, FilterableRepositoryCustom