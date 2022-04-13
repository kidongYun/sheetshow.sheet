package com.kian.yun.sheetshow.sheet.domain.service

import com.kian.yun.sheetshow.sheet.domain.entity.Note
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
interface NoteService {
    fun save(request: Note) : Long

    fun findById(id: Long) : Note

    fun find(pageable: Pageable) : List<Note>

    fun update(request: Note) : Note

    fun delete(id: Long)
}