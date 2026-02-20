package com.example.mentoria.core.domain.util

import java.util.Locale

fun getDeviceLanguage(): String {
    val locale = Locale.getDefault()

    val language = locale.language
    val country = locale.country

    return if (country.isNotEmpty()) {
        "$language-$country"
    } else {
        language
    }
}