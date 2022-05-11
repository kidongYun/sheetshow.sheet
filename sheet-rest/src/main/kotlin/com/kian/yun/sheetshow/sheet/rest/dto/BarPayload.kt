package com.kian.yun.sheetshow.sheet.rest.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.kian.yun.sheetshow.sheet.domain.data.Chord

class BarPayload {
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

    class El {
        data class Req(
            @JsonProperty("barEl") val barEl: String
        )

        data class ReqPost(
            @JsonProperty("barEl") val barEl: String
        )

        data class ReqPut(
            @JsonProperty("barEl") val barEl: String
        )

        data class Res(
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