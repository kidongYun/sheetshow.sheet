package com.kian.yun.sheetshow.sheet.rest.controller

import com.kian.yun.sheetshow.sheet.rest.code.Response
import com.kian.yun.sheetshow.sheet.common.util.toLong
import com.kian.yun.sheetshow.sheet.domain.service.FingeringService
import com.kian.yun.sheetshow.sheet.rest.dto.FingeringPayload
import com.kian.yun.sheetshow.sheet.rest.mapper.FingeringMapper
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/fingering")
class FingeringController(
    private val fingeringMapper: FingeringMapper,
    private val fingeringService: FingeringService
) : FingeringSpec {

    override fun post(request: FingeringPayload.ReqPost): Response<Long>
    = Response.ofSuccess(fingeringService.save(fingeringMapper.ofEntity(request)))

    override fun get(id: String): Response<FingeringPayload.Res>
    = Response.ofSuccess(fingeringMapper.ofRes(fingeringService.findById(toLong(id))))

    override fun getList(pageable: Pageable): Response<List<FingeringPayload.Res>>
    = Response.ofSuccess(fingeringService.find(pageable).map { fingeringMapper.ofRes(it) }.toList())

    override fun getListByChord(chord: String): Response<List<FingeringPayload.Res>>
    = Response.ofSuccess(fingeringService.findByChord(chord).map { fingeringMapper.ofRes(it) }.toList())

    override fun put(id: String, request: FingeringPayload.ReqPut): Response<FingeringPayload.Res>
    = Response.ofSuccess(fingeringMapper.ofRes(fingeringService.update(fingeringMapper.ofEntity(id, request))))

    override fun delete(id: String): Response<Void> {
        fingeringService.delete(toLong(id))
        return Response.ofSuccess()
    }
}