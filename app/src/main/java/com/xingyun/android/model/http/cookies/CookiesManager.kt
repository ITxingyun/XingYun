package com.xingyun.android.model.http.cookies

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

class CookiesManager : CookieJar {


    override fun saveFromResponse(url: HttpUrl, cookies: MutableList<Cookie>) {
        if (cookies.size > 0) {
            for (item in cookies) {
                COOKIE_STORE.add(url, item)
            }
        }
    }

    override fun loadForRequest(url: HttpUrl): MutableList<Cookie> {
        return COOKIE_STORE[url]
    }

    /**
     * 清除所有cookie
     */
    fun clearAllCookies() {
        COOKIE_STORE.removeAll()
    }

    /**
     * 清除指定cookie
     *
     * @param url HttpUrl
     * @param cookie Cookie
     * @return if clear cookies
     */
    fun clearCookies(url: HttpUrl, cookie: Cookie): Boolean {
        return COOKIE_STORE.remove(url, cookie)
    }

    /**
     * 获取cookies
     *
     * @return List<Cookie>
    </Cookie> */
    fun getCookies(): List<Cookie> {
        return COOKIE_STORE.getCookies()
    }


    companion object {
        private val COOKIE_STORE = PersistentCookieStore()
    }

}