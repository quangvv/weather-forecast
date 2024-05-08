package com.android.weather.forecast.utils

sealed class ResourceState<out R> {
    data object Loading : ResourceState<Nothing>()
    data class Success<out T>(val data: T) : ResourceState<T>()
    data class Error(val message: String) : ResourceState<Nothing>()
}