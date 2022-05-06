package com.kian.yun.sheetshow.sheet.domain.repository.helper

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Dummy(
    @Id
    val id : Long,
    val name: String,
    val color: String,
)