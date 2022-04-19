package com.kian.yun.sheetshow.sheet.rest.dto

class BarDto {
    data class ReqPost(
        val no: String,
        val lyrics: String,
        val fingeringId: String,
        val sheetId: String
    )

    data class ReqPut(
        val no: String,
        val lyrics: String,
        val fingeringId: String,
        val sheetId: String
    )

    data class Res(
        val id: String,
        val no: String,
        val lyrics: String,
        val fingeringId: String,
        val sheetId: String
    )
}