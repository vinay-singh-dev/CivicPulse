package com.example.civicpulse.data.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        // Modify request here
        val newRequest = originalRequest.newBuilder()
            .addHeader("X-Demo-Header", "CivicPulse")
            .build()

        return chain.proceed(newRequest)
    }
}
