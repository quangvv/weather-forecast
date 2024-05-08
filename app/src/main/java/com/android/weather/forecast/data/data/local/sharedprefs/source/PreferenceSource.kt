package com.android.weather.forecast.data.data.local.sharedprefs.source

interface PreferenceSource {
    fun saveSessionKey(key: String)
    fun getSessionKey(): String?
    fun removeSessionKey()
}