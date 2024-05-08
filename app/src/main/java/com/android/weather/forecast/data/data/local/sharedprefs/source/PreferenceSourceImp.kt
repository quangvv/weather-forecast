package com.android.weather.forecast.data.data.local.sharedprefs.source

import com.android.weather.forecast.data.data.local.sharedprefs.SharedPreferencesManager
import javax.inject.Inject

class PreferenceSourceImp @Inject constructor(
    private val sharedPreferences: SharedPreferencesManager
) : PreferenceSource {

    override fun saveSessionKey(key: String) {
        sharedPreferences.saveSessionKey(sessionKey = key)
    }

    override fun getSessionKey(): String? {
        return sharedPreferences.getSessionKey()
    }

    override fun removeSessionKey() {
        sharedPreferences.removeSessionKey()
    }
}