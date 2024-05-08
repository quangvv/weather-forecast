package com.android.weather.forecast.data.model

class RequestException(val code: Int, message: String) : Throwable(message)