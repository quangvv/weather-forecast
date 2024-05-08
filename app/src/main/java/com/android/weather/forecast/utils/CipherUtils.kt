package com.android.weather.forecast.utils

import android.annotation.SuppressLint
import timber.log.Timber
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object CipherUtils {
    private val TAG = CipherUtils::class.java.simpleName

    /** Encryption method  */
    private const val ALGORITHM = "AES"
    private const val TRANSFORMATION = "AES/CBC/PKCS5Padding"
    private const val TRANSFORMATION_HELP = "AES/ECB/PKCS5Padding"

    /**
     * Encrypt using AES / CBC / PKCS5Padding method and convert to hexadecimal character string
     */
    fun encryptStringToHexString(key: String, str: String, iv: String): String? {
        val encryptedBytes = encryptBytesToBytes(key, str.toByteArray(), iv)
        return if (encryptedBytes != null) encodeHex(encryptedBytes) else null
    }

    /**
     * Encryption using AES / ECB / PKCS5 Padding method
     */
    fun encryptStringToBytes(key: String, str: String): ByteArray? {
        return encryptBytesToBytes(key, str.toByteArray())
    }

    /**
     * Decode using the AES / CBC / PKCS5Padding method and convert to a character string
     */
    fun decryptHexStringToString(key: String, str: String, iv: String): String? {
        val ret = decryptBytesToBytes(key, decodeHex(str), iv)
        return ret?.let { String(it) }
    }

    /**
     * Decode using the AES / ECB / PKCS5Padding method and convert to a character string
     */
    fun decryptHexByteToString(key: String, bytes: ByteArray): String? {
        val ret = decryptBytesToBytes(key, bytes)
        return ret?.let { String(it) }
    }

    /**
     * Encryption using AES / CBC / PKCS5 Padding method
     */
    private fun encryptBytesToBytes(key: String, bytes: ByteArray, iv: String): ByteArray? {
        try {
            val cipher = Cipher.getInstance(TRANSFORMATION)
            cipher.init(
                Cipher.ENCRYPT_MODE,
                SecretKeySpec(decodeHex(key), ALGORITHM),
                IvParameterSpec(decodeHex(iv))
            )
            return cipher.doFinal(bytes)
        } catch (e: Exception) {
            Timber.tag(TAG).e("encrypt failed")
        }
        return null
    }

    /**
     * Encryption using AES / ECB / PKCS5 Padding method
     */
    @SuppressLint("GetInstance")
    private fun encryptBytesToBytes(key: String, bytes: ByteArray): ByteArray? {
        try {
            val cipher = Cipher.getInstance(TRANSFORMATION_HELP)
            cipher.init(Cipher.ENCRYPT_MODE, SecretKeySpec(decodeHex(key), ALGORITHM))
            return cipher.doFinal(bytes)
        } catch (e: Exception) {
            Timber.tag(TAG).e("encrypt failed")
        }
        return null
    }

    /**
     * Decryption using AES / CBC / PKCS5Padding method
     */
    private fun decryptBytesToBytes(key: String, bytes: ByteArray, iv: String): ByteArray? {
        try {
            val cipher = Cipher.getInstance(TRANSFORMATION)
            cipher.init(
                Cipher.DECRYPT_MODE,
                SecretKeySpec(decodeHex(key), ALGORITHM),
                IvParameterSpec(decodeHex(iv))
            )
            return cipher.doFinal(bytes)
        } catch (e: Exception) {
            Timber.tag(TAG).e("decrypt bytes failed.")
        }
        return null
    }

    /**
     * Decryption using AES / ECB / PKCS5Padding method
     */
    @SuppressLint("GetInstance")
    private fun decryptBytesToBytes(key: String, bytes: ByteArray): ByteArray? {
        try {
            val cipher = Cipher.getInstance(TRANSFORMATION_HELP)
            cipher.init(Cipher.DECRYPT_MODE, SecretKeySpec(decodeHex(key), ALGORITHM))
            return cipher.doFinal(bytes)
        } catch (e: Exception) {
            Timber.tag(TAG).e("decrypt bytes failed.")
        }
        return null
    }

    /**
     * Convert byte string to string
     */
    private fun encodeHex(data: ByteArray): String {
        val digits = charArrayOf(
            '0',
            '1',
            '2',
            '3',
            '4',
            '5',
            '6',
            '7',
            '8',
            '9',
            'a',
            'b',
            'c',
            'd',
            'e',
            'f'
        )
        val len = data.size
        val encoded = CharArray(len shl 1)
        var i = 0
        for (bytes in data) {
            encoded[i++] = digits[240 and bytes.toInt() ushr 4]
            encoded[i++] = digits[15 and bytes.toInt()]
        }
        return String(encoded)
    }

    /**
     * Convert string to byte string
     */
    @Throws(IllegalArgumentException::class)
    private fun decodeHex(str: String): ByteArray {
        val data = str.toLowerCase(Locale.ENGLISH).toCharArray()
        val len = data.size
        require(len and 1 == 0) { "Odd number of characters." }
        val decoded = ByteArray(len shr 1)
        var i = 0
        var j = 0
        while (j < len) {
            var f = toDigit(data[j], j) shl 4
            j++
            f = f or toDigit(data[j], j)
            j++
            decoded[i] = (f and 255).toByte()
            i++
        }
        return decoded
    }

    /**
     * Convert target character to hexadecimal
     */
    @Throws(IllegalArgumentException::class)
    private fun toDigit(ch: Char, index: Int): Int {
        val digit = Character.digit(ch, 16)
        require(digit != -1) { "Illegal hexadecimal character $ch at index $index" }
        return digit
    }
}