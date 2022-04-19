package com.kian.yun.sheetshow.sheet.rest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@ComponentScan(basePackages = ["com.kian.yun.sheetshow.sheet"])
@SpringBootApplication
class SheetRestApplication

fun main(args: Array<String>) {
    runApplication<SheetRestApplication>(*args)
}
