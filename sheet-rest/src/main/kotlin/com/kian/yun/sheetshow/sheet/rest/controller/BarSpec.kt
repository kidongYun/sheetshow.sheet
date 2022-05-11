package com.kian.yun.sheetshow.sheet.rest.controller

import com.kian.yun.sheetshow.sheet.rest.code.Response
import com.kian.yun.sheetshow.sheet.rest.dto.BarPayload
import com.kian.yun.sheetshow.sheet.rest.dto.Filterable
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*

interface BarSpec {
    @PostMapping
    fun post(@RequestBody request: BarPayload.ReqPost) : Response<Long>

    @PostMapping("/el/{id}")
    fun postByEl(@PathVariable id: String, @RequestBody request: BarPayload.El.ReqPost) : Response<Long>

    @PostMapping("/el")
    fun getListByEl(@RequestBody request: BarPayload.El.Req) : Response<List<BarPayload.El.Res>>

    @GetMapping("/{id}")
    fun get(@PathVariable id: String) : Response<BarPayload.Res>

    @GetMapping
    fun getList(pageable: Pageable) : Response<List<BarPayload.Res>>

    @PutMapping("/{id}")
    fun put(@PathVariable id: String, @RequestBody request: BarPayload.ReqPut) : Response<BarPayload.Res>

    @PutMapping("/el/{id}")
    fun putByEl(@PathVariable id: String, @RequestBody request: BarPayload.El.ReqPut) : Response<Long>

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String) : Response<Void>

    @PostMapping("/query")
    fun query(pageable: Pageable, @RequestBody filterable: Filterable) : Response<List<BarPayload.Res>>
}