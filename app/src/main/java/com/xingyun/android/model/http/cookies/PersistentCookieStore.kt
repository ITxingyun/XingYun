package com.xingyun.android.model.http.cookies

import android.text.TextUtils
import android.util.Log
import com.xingyun.android.common.app.WanAndroidApplication
import okhttp3.Cookie
import okhttp3.HttpUrl
import java.io.*
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import kotlin.collections.HashMap
import kotlin.experimental.and

class PersistentCookieStore {
    private val cookiePrefs =
        WanAndroidApplication.getInstance().getSharedPreferences(COOKIE_PREFS, 0)
    private val cookies = HashMap<String, ConcurrentHashMap<String, Cookie>>()

    init {

        //将持久化的cookies缓存到内存中 即map cookies
        val prefsMap = cookiePrefs.all

        for ((key, value) in prefsMap) {
            val cookieNames = (value as String?)?.split(",") ?: emptyList()
            for (name in cookieNames) {
                val encodedCookie = cookiePrefs.getString(name, null)
                encodedCookie?.let {
                    val decodedCookie: Cookie? = decodeCookie(it)
                    if (decodedCookie != null) {
                        if (!cookies.containsKey(key)) {
                            cookies[key] = ConcurrentHashMap()
                        }
                        cookies[key]?.put(name, decodedCookie)
                    }
                }
            }
        }
    }

    private fun getCookieToken(cookie: Cookie): String {
        return cookie.name() + "@" + cookie.domain()
    }

    fun add(url: HttpUrl, cookie: Cookie) {
        val name = getCookieToken(cookie)
        //将cookies缓存到内存中 如果缓存过期 就重置此cookie
        if (!cookie.persistent()) {
            if (!cookies.containsKey(url.host())) {
                cookies[url.host()] = ConcurrentHashMap(10)
            }
            cookies[url.host()]?.put(name, cookie)
        } else {
            if (cookies.containsKey(url.host())) {
                cookies[url.host()]?.remove(name)
            }
        }

        //讲cookies持久化到本地
        cookies[url.host()]?.entries?.let {
            val prefsWriter = cookiePrefs.edit()
            prefsWriter.putString(url.host(), TextUtils.join(",", it))
            prefsWriter.putString(name, encodeCookie(OkHttpCookies(cookie)))
            prefsWriter.apply()
        }
    }

    operator fun get(url: HttpUrl): MutableList<Cookie> {
        val cookieList = mutableListOf<Cookie>()
        if (cookies.containsKey(url.host())) {
            cookies[url.host()]?.let { cookieList.addAll(it.values) }
        }
        return cookieList
    }

    fun removeAll() {
        val prefsWriter = cookiePrefs.edit()
        prefsWriter.clear()
        prefsWriter.apply()
        cookies.clear()
    }

    fun remove(url: HttpUrl, cookie: Cookie): Boolean {
        val name = getCookieToken(cookie)
        return cookies[url.host()]?.run {
            if (contains(name)) {
                remove(name)
                val prefsWriter = cookiePrefs.edit()
                if (cookiePrefs.contains(name)) {
                    prefsWriter.remove(name)
                }

                prefsWriter.putString(url.host(), TextUtils.join(",", cookies[url.host()]!!.keys))
                prefsWriter.apply()
                true
            } else {
                false
            }
        } ?: false
    }

    fun getCookies(): List<Cookie> {
        return cookies.map { it.value }.flatMap { it.values }
    }

    /**
     * cookies 序列化成 string
     *
     * @param cookie 要序列化的cookie
     * @return 序列化之后的string
     */
    private fun encodeCookie(cookie: OkHttpCookies): String? {
        val os = ByteArrayOutputStream()
        try {
            val outputStream = ObjectOutputStream(os)
            outputStream.writeObject(cookie)
        } catch (e: IOException) {
            Log.d(LOG_TAG, "IOException in encodeCookie", e)
            return null
        }
        return byteArrayToHexString(os.toByteArray())
    }

    /**
     * 将字符串反序列化成cookies
     *
     * @param cookieString cookies string
     * @return cookie object
     */
    private fun decodeCookie(cookieString: String): Cookie? {
        val bytes = hexStringToByteArray(cookieString)
        val byteArrayInputStream = ByteArrayInputStream(bytes)
        var cookie: Cookie? = null
        try {
            val objectInputStream = ObjectInputStream(byteArrayInputStream)
            cookie = (objectInputStream.readObject() as OkHttpCookies).getCookies()
        } catch (e: IOException) {
            Log.d(LOG_TAG, "IOException in decodeCookie", e)
        } catch (e: ClassNotFoundException) {
            Log.d(LOG_TAG, "ClassNotFoundException in decodeCookie", e)
        }
        return cookie
    }

    /**
     * 二进制数组转十六进制字符串
     *
     * @param bytes byte array to be converted
     * @return string containing hex values
     */
    private fun byteArrayToHexString(bytes: ByteArray): String? {
        val sb = StringBuilder(bytes.size * 2)
        for (element in bytes) {
            val v: Int = (element and 0xff.toByte()).toInt()
            if (v < 16) {
                sb.append('0')
            }
            sb.append(Integer.toHexString(v))
        }
        return sb.toString().toUpperCase(Locale.US)
    }

    /**
     * 十六进制字符串转二进制数组
     *
     * @param hexString string of hex-encoded values
     * @return decoded byte array
     */
    private fun hexStringToByteArray(hexString: String): ByteArray {
        val len = hexString.length
        val data = ByteArray(len / 2)
        var i = 0
        while (i < len) {
            data[i / 2] = ((Character.digit(hexString[i], 16) shl 4) + Character.digit(
                hexString[i + 1],
                16
            )).toByte()
            i += 2
        }
        return data
    }


    companion object {
        private const val COOKIE_PREFS = "Cookies_Prefs"
        private const val LOG_TAG = "PersistentCookieStore"
    }
}