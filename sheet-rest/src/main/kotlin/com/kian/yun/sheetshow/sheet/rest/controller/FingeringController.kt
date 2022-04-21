package com.kian.yun.sheetshow.sheet.rest.controller

import com.kian.yun.sheetshow.sheet.common.code.Response
import com.kian.yun.sheetshow.sheet.common.util.toLong
import com.kian.yun.sheetshow.sheet.domain.service.FingeringService
import com.kian.yun.sheetshow.sheet.rest.dto.FingeringDto
import com.kian.yun.sheetshow.sheet.rest.dto.Filterable
import com.kian.yun.sheetshow.sheet.rest.mapper.FilterableMapper
import com.kian.yun.sheetshow.sheet.rest.mapper.FingeringMapper
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/fingering")
class FingeringController(
    private val filterableMapper: FilterableMapper,
    private val fingeringMapper: FingeringMapper,
    private val fingeringService: FingeringService
) : FingeringSpec {

    override fun post(request: FingeringDto.ReqPost): Response<Long>
    = Response.ofSuccess(fingeringService.save(fingeringMapper.ofEntity(request)))

    override fun get(id: String): Response<FingeringDto.Res>
    = Response.ofSuccess(fingeringMapper.ofRes(fingeringService.findById(toLong(id))))

    override fun getList(pageable: Pageable): Response<List<FingeringDto.Res>>
    = Response.ofSuccess(fingeringService.find(pageable).map { fingeringMapper.ofRes(it) }.toList())

    override fun put(id: String, request: FingeringDto.ReqPut): Response<FingeringDto.Res>
    = Response.ofSuccess(fingeringMapper.ofRes(fingeringService.update(fingeringMapper.ofEntity(id, request))))

    override fun delete(id: String): Response<Void> {
        fingeringService.delete(toLong(id))
        return Response.ofSuccess()
    }

    override fun query(pageable: Pageable, filterable: Filterable): Response<List<FingeringDto.Res>>
    = Response.ofSuccess(fingeringService.query(filterableMapper.ofDomain(filterable), pageable).map { fingeringMapper.ofRes(it) })
}