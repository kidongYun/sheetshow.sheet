package com.kian.yun.sheetshow.sheet.rest.controller

import com.kian.yun.sheetshow.sheet.common.code.Response
import com.kian.yun.sheetshow.sheet.rest.dto.QueryDto
import com.kian.yun.sheetshow.sheet.rest.dto.SheetDto
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*

interface SheetSpec {
    @PostMapping
    fun post(@RequestBody request: SheetDto.ReqPost) : Response<Long>

    @GetMapping("/{id}")
    fun get(@PathVariable id: String) : Response<SheetDto.Res>

    @PutMapping("/{id}")
    fun put(@PathVariable id: String, @RequestBody request: SheetDto.ReqPut) : Response<SheetDto.Res>

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String) : Response<Void>

    @PostMapping("/query")
    fun query(pageable: Pageable, @RequestBody request: QueryDto.Req) : Response<List<SheetDto.Res>>
}