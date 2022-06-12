package com.kian.yun.sheetshow.sheet.domain.repository

import com.kian.yun.sheetshow.sheet.domain.entity.QBar
import com.querydsl.jpa.impl.JPAQueryFactory

class BarRepositoryCustomImpl(
    private val queryFactory: JPAQueryFactory
) : BarRepositoryCustom {
    override fun deleteAllBySheetId(sheetId: Long) {
        queryFactory.delete(QBar.bar)
            .where(QBar.bar.sheetId.eq(sheetId))
            .execute()
    }
}