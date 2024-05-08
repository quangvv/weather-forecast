package com.android.weather.forecast.data.data.local.sharedprefs

import android.content.Context
import android.content.SharedPreferences
import android.text.TextUtils
import com.android.weather.forecast.R
import com.android.weather.forecast.utils.CipherUtils
import org.json.JSONArray
import timber.log.Timber
import java.util.ArrayList
import javax.inject.Inject

class SharedPreferencesManager @Inject constructor(
    context: Context,
    name: String?
) {
    private var prefs: SharedPreferences? = null
    private var helper: SharedPreferencesHelper? = null

    init {
        prefs = context.getSharedPreferences(name, Context.MODE_PRIVATE)
        helper = SharedPreferencesHelper(context)
    }

    companion object {
        private const val KEY_TOKEN = "weather_session"

        private const val ON = "1"
        private const val OFF = "0"
    }

    fun saveSessionKey(sessionKey: String?) {
        helper!!.save(KEY_TOKEN, sessionKey)
    }

    fun getSessionKey(): String? {
        return helper!!.loadString(KEY_TOKEN)
    }

    fun hasSessionKey(): Boolean {
        return !TextUtils.isEmpty(
            helper
                ?.loadString(KEY_TOKEN)
        )
    }

    fun removeSessionKey() {
        helper!!.remove(KEY_TOKEN)
    }

    private inner class SharedPreferencesHelper(
        private val context: Context,
        private val secretKey: String = context.getString(R.string.secret_key),
        private val iv: String = context.getString(R.string.iv)
    ) {

        fun clearPrefs() {
            if (prefs != null) {
                prefs!!.edit().clear().apply()
            }
        }

        fun containsKey(containsKey: String): Boolean {
            var key = containsKey
            if (key.isNotEmpty()) {
                key = CipherUtils.encryptStringToHexString(secretKey, key, iv).toString()
            }
            return prefs!!.contains(key)
        }

        fun save(saveKey: String?, string: String?) {
            var key = saveKey
            val editor = prefs!!.edit()
            key = key?.let { CipherUtils.encryptStringToHexString(secretKey, it, iv) }
            editor.putString(key,
                string?.let { CipherUtils.encryptStringToHexString(secretKey, it, iv) })
            editor.apply()
        }

        fun save(key: String?, value: Int) {
            save(key, value.toString())
        }

        fun save(key: String?, value: Long) {
            save(key, value.toString())
        }

        fun save(key: String?, value: Float) {
            save(key, value.toString())
        }

        fun save(key: String?, flag: Boolean) {
            save(key, if (flag) ON else OFF)
        }

        fun save(key: String?, list: ArrayList<*>) {
            save(key, list.toString())
        }

        fun loadString(loadkey: String): String? {
            var key: String? = loadkey
            key = key?.let { CipherUtils.encryptStringToHexString(secretKey, it, iv) }
            val string = prefs!!.getString(key, null)
            return if (string != null) CipherUtils.decryptHexStringToString(
                secretKey,
                string,
                iv
            ) else null
        }

        fun loadInt(key: String, defaultValue: Int = -1): Int {
            val value = loadString(key)
            return value?.toInt() ?: defaultValue
        }

        fun loadLong(key: String, defaultValue: Long = -1L): Long {
            val value = loadString(key)
            return value?.toLong() ?: defaultValue
        }

        private fun loadFloat(key: String, defaultValue: Float = -1F): Float {
            val value = loadString(key)
            return value?.toFloat() ?: defaultValue
        }

        private fun loadBoolean(key: String, defaultValue: Boolean): Boolean {
            val value = loadString(key)
            return if (value != null) TextUtils.equals(
                loadString(key), ON
            ) else defaultValue
        }

        private fun loadStringArray(key: String): ArrayList<String> {
            val value = loadString(key)
            val list = ArrayList<String>()
            if (value != null) {
                try {
                    val array = JSONArray(value)
                    var i = 0
                    val length: Int = array.length()
                    while (i < length) {
                        list.add(array.optString(i))
                        i++
                    }
                } catch (e: Exception) {
                    Timber.tag("SharedPrefManager").e(e)
                }
            }
            return list
        }

        fun remove(removeKey: String?) {
            var key = removeKey
            val editor = prefs!!.edit()
            key = key?.let { CipherUtils.encryptStringToHexString(secretKey, it, iv) }
            editor.remove(key)
            editor.apply()
        }
    }
}
