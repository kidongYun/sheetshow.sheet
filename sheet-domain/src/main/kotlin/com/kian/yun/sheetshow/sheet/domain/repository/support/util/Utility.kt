package com.kian.yun.sheetshow.filterable.util

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.lang.reflect.Field

inline fun <reified T> T.logger(): Logger = LoggerFactory.getLogger(T::class.java)

fun isNotStatic(field: Field)
= !java.lang.reflect.Modifier.isStatic(field.modifiers)