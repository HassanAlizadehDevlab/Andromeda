package com.android.data.network

import com.android.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.security.MessageDigest

/**
 * Created by hassanalizadeh on 28,August,2020
 */
class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        val ts = System.currentTimeMillis()
        val bytes = (ts.toString() + BuildConfig.PRIVATE_KEY + BuildConfig.PUBLIC_KEY).md5

        val url = request.url.newBuilder()
            .addQueryParameter("apikey", BuildConfig.PUBLIC_KEY)
            .addQueryParameter("ts", ts.toString())
            .addQueryParameter("hash", bytes)
            .build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }

    private val String.md5: String
        get() {
            val bytes = MessageDigest.getInstance("MD5").digest(this.toByteArray())
            return bytes.joinToString("") {
                "%02x".format(it)
            }
        }

}