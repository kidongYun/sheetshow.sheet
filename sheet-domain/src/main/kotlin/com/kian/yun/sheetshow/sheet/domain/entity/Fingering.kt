package com.kian.yun.sheetshow.sheet.domain.entity

import com.kian.yun.sheetshow.sheet.domain.data.Note
import org.springframework.data.redis.core.RedisHash
import javax.persistence.Id

@RedisHash("fingering")
class Fingering(
    @Id
    var id: Long?,

    val type: String,

    val chord: String,

    val notes: List<Note>
)