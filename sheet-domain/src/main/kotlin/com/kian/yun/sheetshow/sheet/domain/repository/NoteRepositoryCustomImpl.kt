package com.kian.yun.sheetshow.sheet.domain.repository

import com.querydsl.jpa.impl.JPAQueryFactory

class NoteRepositoryCustomImpl(
    private val queryFactory: JPAQueryFactory
) : NoteRepositoryCustom