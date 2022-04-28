package com.kian.yun.sheetshow.sheet.rest.controller

import com.kian.yun.sheetshow.filterable.queryOptions.QueryOptions
import com.kian.yun.sheetshow.sheet.rest.code.Response
import com.kian.yun.sheetshow.sheet.common.util.toLong
import com.kian.yun.sheetshow.sheet.domain.entity.Bar
import com.kian.yun.sheetshow.sheet.domain.entity.Fingering
import com.kian.yun.sheetshow.sheet.domain.entity.Sheet
import com.kian.yun.sheetshow.sheet.domain.repository.support.SimpleCondition
import com.kian.yun.sheetshow.sheet.domain.service.BarService
import com.kian.yun.sheetshow.sheet.domain.service.FingeringService
import com.kian.yun.sheetshow.sheet.domain.service.SheetService
import com.kian.yun.sheetshow.sheet.rest.dto.Filterable
import com.kian.yun.sheetshow.sheet.rest.dto.SheetDto
import com.kian.yun.sheetshow.sheet.rest.mapper.FilterableMapper
import com.kian.yun.sheetshow.sheet.rest.mapper.SheetMapper
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/sheet")
class SheetController(
    private val filterableMapper: FilterableMapper,
    private val sheetMapper: SheetMapper,
    private val sheetService: SheetService,
    private val barService: BarService,
    private val fingeringService: FingeringService,
) : SheetSpec {

    override fun post(request: SheetDto.ReqPost): Response<Long>
    = Response.ofSuccess(sheetService.save(sheetMapper.ofEntity(request)))

    override fun postDetail(request: SheetDto.Detail.ReqPost): Response<Long>
    = Response.ofSuccess(sheetService.saveDetail(sheetMapper.ofEntity(request), request.barEl))

    override fun get(id: String): Response<SheetDto.Res>
    = Response.ofSuccess(sheetMapper.ofRes(sheetService.findById(toLong(id))))

    override fun getList(pageable: Pageable): Response<List<SheetDto.Res>>
    = Response.ofSuccess(sheetService.find(pageable).map { sheetMapper.ofRes(it) }.toList())

    override fun getDetail(id: String): Response<SheetDto.Detail.Res> {
        val sheet: Sheet = sheetService.findById(toLong(id))
        val bars: List<Bar> = barService.query(SimpleCondition.of("sheetId", listOf(sheet.id.toString()), QueryOptions.EQUAL))
        val fingering: List<Fingering> = bars.map { fingeringService.findById(it.fingeringId) }

        return Response.ofSuccess(sheetMapper.ofDetailRes(sheet, bars, fingering))
    }

    override fun put(id: String, request: SheetDto.ReqPut): Response<SheetDto.Res>
    = Response.ofSuccess(sheetMapper.ofRes(sheetService.update(sheetMapper.ofEntity(id, request))))

    override fun delete(id: String): Response<Void> {
        sheetService.delete(toLong(id))
        return Response.ofSuccess()
    }

    override fun query(pageable: Pageable, filterable: Filterable): Response<List<SheetDto.Res>>
    = Response.ofSuccess(sheetService.query(filterableMapper.ofDomain(filterable), pageable).map { sheetMapper.ofRes(it) })
}