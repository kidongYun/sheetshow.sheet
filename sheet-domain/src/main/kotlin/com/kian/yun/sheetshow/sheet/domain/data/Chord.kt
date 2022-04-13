package com.kian.yun.sheetshow.sheet.domain.data

data class Chord(
    val key: Key,
    val harmony: Harmony
) {
    override fun toString(): String
            = this.key.name + this.harmony.name
}