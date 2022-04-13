package com.kian.yun.sheetshow.sheet.domain.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Fingering(
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    val id: Long?,

    val type: String,

    val chord: String
)