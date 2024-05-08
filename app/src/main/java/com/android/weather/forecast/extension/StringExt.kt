package com.android.weather.forecast.extension

class StringExt {
    fun String.capitalizeFirstChar(): String {
        return replaceFirstChar {
            it.uppercaseChar()
        }
    }
}