package com.kian.yun.sheetshow.sheet.rest.dto

class NotePayload {
    data class ReqPost(
        val line: String,
        val space: String,
        val fingeringId: String
    )

    data class ReqPut(
        val line: String,
        val space: String,
        val fingeringId: String
    )

    data class Res(
        val id: String,
        val line: String,
        val space: String,
        val fingeringId: String
    )
}