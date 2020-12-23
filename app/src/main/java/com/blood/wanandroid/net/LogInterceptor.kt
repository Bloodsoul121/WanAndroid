package com.blood.wanandroid.net

import okhttp3.*
import okio.Buffer
import timber.log.Timber
import java.io.IOException

class LogInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        Timber.i(String.format("%1\$s->%2\$s", request.method, request.url))
        Timber.i("Headers:%s", request.headers)
        if (request.body != null) {
            Timber.i("RequestBody:%s", bodyToString(request.body))
        }
        val response: okhttp3.Response = chain.proceed(chain.request())
        val mediaType = response.body!!.contentType()
        val responseBody = response.body!!.string()
        Timber.i("ResponseBody:$responseBody")
        return response.newBuilder().body(ResponseBody.create(mediaType, responseBody)).build()
    }

    private fun bodyToString(request: RequestBody?): String? {
        if (request != null) {
            try {
                val copy: RequestBody = request
                val buffer = Buffer()
                copy.writeTo(buffer)
                return buffer.readUtf8()
            } catch (e: IOException) {
                Timber.e(e, "Did not work.")
            }
        }
        return null
    }
}