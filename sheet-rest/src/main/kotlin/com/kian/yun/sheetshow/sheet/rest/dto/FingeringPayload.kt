package com.kian.yun.sheetshow.sheet.rest.dto

class FingeringPayload {
    data class ReqPost(
        val type: String,
        val chord: String,
        val notes: List<Note>
    )

    data class ReqPut(
        val type: String,
        val chord: String,
        val notes: List<Note>
    )

    data class Res(
        val id: String,
        val type: String,
        val chord: String,
        val notes: List<Note>
    )

    data class Note(
        val line: String,
        val space: String
    )
}