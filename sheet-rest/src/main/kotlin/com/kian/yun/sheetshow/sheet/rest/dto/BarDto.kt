package com.kian.yun.sheetshow.sheet.rest.dto

import com.fasterxml.jackson.annotation.JsonProperty

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

    class El {
        data class ReqPost(
            @JsonProperty("barEl") val barEl: String
        )

        data class ReqPut(
            @JsonProperty("barEl") val barEl: String
        )
    }
}