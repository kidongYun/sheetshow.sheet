package com.kian.yun.sheetshow.sheet.repository

import com.kian.yun.sheetshow.sheet.domain.entity.Bar
import org.springframework.data.jpa.repository.JpaRepository

interface BarRepository : JpaRepository<Bar, Long>, BarRepositoryCustom {
    fun findAllByLyrics(lyrics: String)
}