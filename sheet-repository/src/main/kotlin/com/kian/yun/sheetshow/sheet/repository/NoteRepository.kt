package com.kian.yun.sheetshow.sheet.repository

import com.kian.yun.sheetshow.sheet.domain.entity.Note
import org.springframework.data.jpa.repository.JpaRepository

interface NoteRepository : JpaRepository<Note, Long>, NoteRepositoryCustom