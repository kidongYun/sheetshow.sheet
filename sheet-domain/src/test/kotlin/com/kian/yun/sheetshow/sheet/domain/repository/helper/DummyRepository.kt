package com.kian.yun.sheetshow.sheet.domain.repository.helper

import org.springframework.data.jpa.repository.JpaRepository

interface DummyRepository : JpaRepository<Dummy, Long> {
}