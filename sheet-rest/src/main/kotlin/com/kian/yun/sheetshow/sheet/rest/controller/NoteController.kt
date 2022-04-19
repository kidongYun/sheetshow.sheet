package com.kian.yun.sheetshow.sheet.rest.controller

import com.kian.yun.sheetshow.sheet.common.code.Response
import com.kian.yun.sheetshow.sheet.common.util.toLong
import com.kian.yun.sheetshow.sheet.domain.service.NoteService
import com.kian.yun.sheetshow.sheet.rest.dto.NoteDto
import com.kian.yun.sheetshow.sheet.rest.mapper.NoteMapper
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/note")
class NoteController(
    private val noteMapper: NoteMapper,
    private val noteService: NoteService
) : NoteSpec {

    override fun post(request: NoteDto.ReqPost): Response<Long>
    = Response.ofSuccess(noteService.save(noteMapper.ofEntity(request)))

    override fun get(id: String): Response<NoteDto.Res>
    = Response.ofSuccess(noteMapper.ofRes(noteService.findById(toLong(id))))

    override fun getList(pageable: Pageable): Response<List<NoteDto.Res>>
    = Response.ofSuccess(noteService.find(pageable).map { noteMapper.ofRes(it) }.toList())

    override fun put(id: String, request: NoteDto.ReqPut): Response<NoteDto.Res>
    = Response.ofSuccess(noteMapper.ofRes(noteService.update(noteMapper.ofEntity(id, request))))

    override fun delete(id: String): Response<Void> {
        noteService.delete(toLong(id))
        return Response.ofSuccess()
    }
}