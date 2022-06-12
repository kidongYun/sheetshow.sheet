package com.kian.yun.sheetshow.sheet.rest.dto

class SheetPayload {
    data class ReqPost(
        val name: String,
        val author: String,
        val creatorEmail: String
    )

    data class ReqPut(
        val name: String,
        val author: String,
        val creatorEmail: String
    )

    data class Res(
        val id: String,
        val name: String,
        val author: String,
        val creatorEmail: String
    )

    class Detail {
        data class ReqPost(
            val name: String,
            val author: String,
            val creatorEmail: String,
            val barEl: String
        )

        data class ReqPut(
            val name: String,
            val author: String,
            val creatorEmail: String,
            val barEl: String
        )

        data class Res(
            val id: String,
            val name: String,
            val author: String,
            val creatorEmail: String,
            val bars: List<Bar>
        )

        data class Bar(
            val no: String,
            val lyrics: String,
            val fingering: Fingering
        )

        data class Fingering(
            val type: String,
            val chord: String,
            val notes: List<Note>
        )

        data class Note(
            val line: String,
            val space: String
        )
    }
}