package com.kian.yun.sheetshow.sheet.common.code

enum class SheetCode(
    private val code: String,
    private val desc: String
) {
    /* COMMON */
    SUCCESS("CM0000", "it's success"),
    STATUS_UNKNOWN_ERROR("CM1000", "Unknown error is happened."),
    DATA_IS_NOT_FOUND("CM1001", "Data is not found."),
    NUMBER_FORMAT_PARSE_FAILED("CM1002", "NumberFormatException is occurred."),

    /* QUERYDSL */
    CONDITION_IS_NOT_MATCHED("QL1000", "condition is not matched"),

    /* BAR EL PARSING */
    VERTICAL_LINE_IN_NOT_EXISTED("BP1000", "vertical line should contain to generate bar parser but it is not existed")
    ;
}