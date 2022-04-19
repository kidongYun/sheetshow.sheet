package com.kian.yun.sheetshow.sheet.rest.dto

class SheetDto {
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
}