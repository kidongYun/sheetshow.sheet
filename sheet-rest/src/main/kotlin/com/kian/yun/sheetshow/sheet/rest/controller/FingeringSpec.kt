package com.kian.yun.sheetshow.sheet.rest.controller

import com.kian.yun.sheetshow.sheet.rest.code.Response
import com.kian.yun.sheetshow.sheet.rest.dto.FingeringPayload
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.*

interface FingeringSpec {
    @PostMapping
    fun post(@RequestBody request: FingeringPayload.ReqPost) : Response<Long>

    @GetMapping("/{id}")
    fun get(@PathVariable id: String) : Response<FingeringPayload.Res>

    @GetMapping
    fun getList(pageable: Pageable) : Response<List<FingeringPayload.Res>>

    @GetMapping("/chord/{chord}")
    fun getListByChord(@PathVariable chord: String) : Response<List<FingeringPayload.Res>>

    @PutMapping("/{id}")
    fun put(@PathVariable id: String, @RequestBody request: FingeringPayload.ReqPut) : Response<FingeringPayload.Res>

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String) : Response<Void>
}