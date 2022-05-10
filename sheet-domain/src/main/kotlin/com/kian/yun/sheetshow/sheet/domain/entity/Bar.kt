package com.kian.yun.sheetshow.sheet.domain.entity

import com.kian.yun.sheetshow.sheet.common.util.toLong
import com.kian.yun.sheetshow.sheet.domain.dto.BarDto
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Bar(
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    val id: Long?,

    val no: Long,

    val lyrics: String,

    val fingeringId: Long,

    val sheetId: Long
) {
    companion object {
        fun of(src : BarDto.Parser, fingeringId: Long) : Bar {
            return Bar(null, toLong(src.no), src.lyrics, fingeringId, 0)
        }
    }
}