package com.kian.yun.sheetshow.sheet.domain.repository

import com.querydsl.jpa.impl.JPAQueryFactory

class FingeringRepositoryCustomImpl(
    private val queryFactory: JPAQueryFactory
) : FingeringRepositoryCustom