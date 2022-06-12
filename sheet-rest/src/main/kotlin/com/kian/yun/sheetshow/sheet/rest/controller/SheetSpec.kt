package com.kian.yun.sheetshow.sheet.rest.controller

import com.kian.yun.sheetshow.sheet.rest.code.Response
import com.kian.yun.sheetshow.sheet.rest.dto.Filterable
import com.kian.yun.sheetshow.sheet.rest.dto.SheetPayload
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*

interface SheetSpec {
    @PostMapping
    fun post(@RequestBody request: SheetPayload.ReqPost) : Response<Long>

    @PostMapping("/detail")
    fun postDetail(@RequestBody request: SheetPayload.Detail.ReqPost) : Response<Long>

    @GetMapping("/{id}")
    fun get(@PathVariable id: String) : Response<SheetPayload.Res>

    @GetMapping
    fun getList(pageable: Pageable) : Response<List<SheetPayload.Res>>

    @GetMapping("/detail/{id}")
    fun getDetail(@PathVariable id: String) : Response<SheetPayload.Detail.Res>

    @PutMapping("/{id}")
    fun put(@PathVariable id: String, @RequestBody request: SheetPayload.ReqPut) : Response<SheetPayload.Res>

    @PutMapping("/detail/{id}")
    fun putDetail(@PathVariable id: String, @RequestBody request: SheetPayload.Detail.ReqPut) : Response<Long>

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String) : Response<Void>

    @PostMapping("/query")
    fun query(pageable: Pageable, @RequestBody filterable: Filterable) : Response<List<SheetPayload.Res>>
}