package com.kian.yun.sheetshow.sheet.rest.controller

import com.kian.yun.sheetshow.sheet.common.code.Response
import com.kian.yun.sheetshow.sheet.rest.dto.FingeringDto
import com.kian.yun.sheetshow.sheet.rest.dto.Filterable
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*

interface FingeringSpec {
    @PostMapping
    fun post(@RequestBody request: FingeringDto.ReqPost) : Response<Long>

    @GetMapping("/{id}")
    fun get(@PathVariable id: String) : Response<FingeringDto.Res>

    @GetMapping
    fun getList(pageable: Pageable) : Response<List<FingeringDto.Res>>

    @GetMapping("/chord/{chord}")
    fun getListByChord(@PathVariable chord: String) : Response<List<FingeringDto.Res>>

    @PutMapping("/{id}")
    fun put(@PathVariable id: String, @RequestBody request: FingeringDto.ReqPut) : Response<FingeringDto.Res>

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String) : Response<Void>
}