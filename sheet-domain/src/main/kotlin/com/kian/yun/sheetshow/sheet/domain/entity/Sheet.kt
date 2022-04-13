package com.kian.yun.sheetshow.sheet.domain.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Sheet(
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    val id: Long?,

    val name: String,

    val author: String,

    val creatorEmail: String,
)