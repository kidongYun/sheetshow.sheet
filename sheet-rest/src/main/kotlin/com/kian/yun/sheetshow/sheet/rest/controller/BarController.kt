package com.kian.yun.sheetshow.sheet.rest.controller

import com.kian.yun.sheetshow.sheet.rest.code.Response
import com.kian.yun.sheetshow.sheet.common.util.toLong
import com.kian.yun.sheetshow.sheet.domain.service.BarService
import com.kian.yun.sheetshow.sheet.rest.dto.BarDto
import com.kian.yun.sheetshow.sheet.rest.dto.Filterable
import com.kian.yun.sheetshow.sheet.rest.mapper.BarMapper
import com.kian.yun.sheetshow.sheet.rest.mapper.FilterableMapper

import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/bar")
class BarController(
    private val filterableMapper: FilterableMapper,
    private val barMapper: BarMapper,
    private val barService: BarService
) : BarSpec {

    override fun post(request: BarDto.ReqPost): Response<Long>
    = Response.ofSuccess(barService.save(barMapper.ofEntity(request)))

    override fun postByEl(id: String, request: BarDto.El.ReqPost): Response<Long>
    = Response.ofSuccess(barService.saveByEl(toLong(id), request.barEl))

    override fun get(id: String): Response<BarDto.Res>
    = Response.ofSuccess(barMapper.ofRes(barService.findById(toLong(id))))

    override fun getList(pageable: Pageable): Response<List<BarDto.Res>>
    = Response.ofSuccess(barService.find(pageable).map { barMapper.ofRes(it) }.toList())

    override fun put(id: String, request: BarDto.ReqPut): Response<BarDto.Res>
    = Response.ofSuccess(barMapper.ofRes(barService.update(barMapper.ofEntity(id, request))))

    override fun putByEl(id: String, request: BarDto.El.ReqPut): Response<Long>
    = Response.ofSuccess(barService.updateByEl(toLong(id), request.barEl))

    override fun delete(id: String): Response<Void> {
        barService.delete(toLong(id))
        return Response.ofSuccess()
    }

    override fun query(pageable: Pageable, filterable: Filterable): Response<List<BarDto.Res>>
    = Response.ofSuccess(barService.query(filterableMapper.ofDomain(filterable), pageable).map { barMapper.ofRes(it) })
}