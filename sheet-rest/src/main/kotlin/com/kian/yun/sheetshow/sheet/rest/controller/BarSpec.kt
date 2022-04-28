package com.kian.yun.sheetshow.sheet.rest.controller

import com.kian.yun.sheetshow.sheet.rest.code.Response
import com.kian.yun.sheetshow.sheet.rest.dto.BarDto
import com.kian.yun.sheetshow.sheet.rest.dto.Filterable
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*

interface BarSpec {
    @PostMapping
    fun post(@RequestBody request: BarDto.ReqPost) : Response<Long>

    @GetMapping("/{id}")
    fun get(@PathVariable id: String) : Response<BarDto.Res>

    @GetMapping
    fun getList(pageable: Pageable) : Response<List<BarDto.Res>>

    @PutMapping("/{id}")
    fun put(@PathVariable id: String, @RequestBody request: BarDto.ReqPut) : Response<BarDto.Res>

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String) : Response<Void>

    @PostMapping("/query")
    fun query(pageable: Pageable, @RequestBody filterable: Filterable) : Response<List<BarDto.Res>>
}