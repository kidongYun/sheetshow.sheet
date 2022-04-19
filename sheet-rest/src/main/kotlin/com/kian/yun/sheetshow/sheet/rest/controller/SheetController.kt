package com.kian.yun.sheetshow.sheet.rest.controller

import com.kian.yun.sheetshow.sheet.common.code.Response
import com.kian.yun.sheetshow.sheet.common.util.toLong
import com.kian.yun.sheetshow.sheet.domain.service.SheetService
import com.kian.yun.sheetshow.sheet.rest.dto.QueryDto
import com.kian.yun.sheetshow.sheet.rest.dto.SheetDto
import com.kian.yun.sheetshow.sheet.rest.mapper.SheetMapper
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/sheet")
class SheetController(
    private val sheetMapper: SheetMapper,
    private val sheetService: SheetService
) : SheetSpec {

    override fun post(request: SheetDto.ReqPost): Response<Long>
    = Response.ofSuccess(sheetService.save(sheetMapper.ofEntity(request)))

    override fun get(id: String): Response<SheetDto.Res>
    = Response.ofSuccess(sheetMapper.ofRes(sheetService.findById(toLong(id))))

    override fun put(id: String, request: SheetDto.ReqPut): Response<SheetDto.Res>
    = Response.ofSuccess(sheetMapper.ofRes(sheetService.update(sheetMapper.ofEntity(id, request))))

    override fun delete(id: String): Response<Void> {
        sheetService.delete(toLong(id))
        return Response.ofSuccess()
    }

    override fun query(pageable: Pageable, request: QueryDto.Req): Response<List<SheetDto.Res>> {
        TODO("Not yet implemented")
    }
//    = Response.ofSuccess(sheetService.query(filterableMapper.ofDomain(request), pageable).map { sheetMapper.ofRes(it) })
}