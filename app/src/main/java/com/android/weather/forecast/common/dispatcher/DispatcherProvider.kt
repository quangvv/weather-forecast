package com.android.weather.forecast.common.dispatcher

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherProvider {
    fun io(): CoroutineDispatcher

    fun main(): CoroutineDispatcher

    fun default(): CoroutineDispatcher
}