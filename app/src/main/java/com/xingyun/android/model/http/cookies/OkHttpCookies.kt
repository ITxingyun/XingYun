package com.xingyun.android.model.http.cookies

import okhttp3.Cookie
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class OkHttpCookies(@Transient private val cookies: Cookie) {
    @Transient
    private lateinit var clientCookies: Cookie

    fun getCookies(): Cookie {
        var bestCookies = cookies
        if (::clientCookies.isInitialized) {
            bestCookies = clientCookies
        }
        return bestCookies
    }

    @Throws(IOException::class)
    private fun writeObject(out: ObjectOutputStream) {
        with(out) {
            writeObject(cookies.name())
            writeObject(cookies.value())
            writeLong(cookies.expiresAt())
            writeObject(cookies.domain())
            writeObject(cookies.path())
            writeBoolean(cookies.secure())
            writeBoolean(cookies.httpOnly())
            writeBoolean(cookies.hostOnly())
            writeBoolean(cookies.persistent())
        }
    }

    @Throws(IOException::class, ClassNotFoundException::class)
    private fun readObject(inputString: ObjectInputStream) {
        with(inputString) {
            val name = readObject() as String
            val value = readObject() as String
            val domain = readObject() as String
            val path = readObject() as String
            val expiresAt = readLong()
            val secure = readBoolean()
            val httpOnly = readBoolean()
            val hostOnly = readBoolean()
            Cookie.Builder()
                    .name(name)
                    .value(value)
                    .expiresAt(expiresAt)
                    .run {
                        if (hostOnly) hostOnlyDomain(domain) else domain(domain)
                        path(path)
                        if (secure) httpOnly()
                        if (httpOnly) httpOnly()
                        build()
                    }.also { clientCookies = it }
        }
    }
}