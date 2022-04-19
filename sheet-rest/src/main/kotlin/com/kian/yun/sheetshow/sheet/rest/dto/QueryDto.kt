package com.kian.yun.sheetshow.sheet.rest.dto

import com.fasterxml.jackson.annotation.JsonProperty

class QueryDto {
    data class Req(
        @JsonProperty("conditions") val conditions: List<Condition>,
        @JsonProperty("conditionOption") val conditionOption: String
    ) {
        data class Condition(
            @JsonProperty("target") val target: String,
            @JsonProperty("values") val values: List<String>,
            @JsonProperty("queryOption") val queryOption: String
        )
    }
}