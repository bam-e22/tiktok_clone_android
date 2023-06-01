package com.example.tiktok.ui.utils

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun convertToDate(epochMillis: Long?): String {
    return if (epochMillis == null) {
        ""
    } else {
        Instant.ofEpochMilli(epochMillis)
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime()
            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    }
}
