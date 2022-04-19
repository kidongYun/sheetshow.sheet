package com.kian.yun.sheetshow.sheet.rest.dto

class FingeringDto {
    data class ReqPost(
        val type: String,
        val chord: String
    )

    data class ReqPut(
        val type: String,
        val chord: String
    )

    data class Res(
        val id: String,
        val type: String,
        val chord: String
    )
}