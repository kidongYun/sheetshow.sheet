package com.kian.yun.sheetshow.sheet.domain.service

import com.kian.yun.sheetshow.sheet.common.code.SheetCode
import com.kian.yun.sheetshow.sheet.common.exception.SheetException
import com.kian.yun.sheetshow.sheet.domain.entity.Note
import com.kian.yun.sheetshow.sheet.domain.repository.NoteRepository
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class NoteServiceImpl(
    private val noteRepository: NoteRepository
) : NoteService {

    override fun save(request: Note): Long
            = noteRepository.save(request).id ?: throw SheetException(SheetCode.DATA_IS_NOT_FOUND)

    override fun findById(id: Long): Note
            = noteRepository.findByIdOrNull(id) ?: throw SheetException(SheetCode.DATA_IS_NOT_FOUND)

    override fun find(pageable: Pageable): List<Note>
            = noteRepository.findAll(pageable).toList()

    override fun update(request: Note): Note {
        noteRepository.save(request)
        return this.findById(request.id ?: throw SheetException(SheetCode.DATA_IS_NOT_FOUND, "request.id is null"))
    }

    override fun delete(id: Long) {
        noteRepository.delete(this.findById(id))
    }
}