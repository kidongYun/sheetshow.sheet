package com.kian.yun.sheetshow.sheet.rest.controller

import com.kian.yun.sheetshow.sheet.common.code.Response
import com.kian.yun.sheetshow.sheet.rest.dto.Filterable
import com.kian.yun.sheetshow.sheet.rest.dto.FingeringDto
import com.kian.yun.sheetshow.sheet.rest.dto.NoteDto
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*

interface NoteSpec {
    @PostMapping
    fun post(@RequestBody request: NoteDto.ReqPost) : Response<Long>

    @GetMapping("/{id}")
    fun get(@PathVariable id: String) : Response<NoteDto.Res>

    @GetMapping
    fun getList(pageable: Pageable) : Response<List<NoteDto.Res>>

    @PutMapping("/{id}")
    fun put(@PathVariable id: String, @RequestBody request: NoteDto.ReqPut) : Response<NoteDto.Res>

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String) : Response<Void>

    @PostMapping("/query")
    fun query(pageable: Pageable, @RequestBody filterable: Filterable) : Response<List<NoteDto.Res>>
}