package com.kian.yun.sheetshow.sheet.domain.repository

import com.querydsl.jpa.impl.JPAQueryFactory

class SheetRepositoryCustomImpl(
    private val queryFactory: JPAQueryFactory
) : SheetRepositoryCustom