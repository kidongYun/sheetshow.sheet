package com.kian.yun.sheetshow.sheet.domain.repository

import com.querydsl.jpa.impl.JPAQueryFactory

class BarRepositoryCustomImpl(
    private val queryFactory: JPAQueryFactory
) : BarRepositoryCustom