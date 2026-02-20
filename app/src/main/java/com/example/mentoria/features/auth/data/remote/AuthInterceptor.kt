package com.example.mentoria.features.auth.data.remote

import com.example.mentoria.features.auth.data.local.SessionManager
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val sesion: SessionManager,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = sesion.getTokenFromMemory()

        println("AuthInterceptor: Intentando usar el token: $token")

        val request = chain.request().newBuilder()
            .apply {
                token?.let {
                    addHeader("Authorization", "Bearer $it")
                }
            }.build()

        val response = chain.proceed(request)

        if (response.code == 401) {
            println("AuthInterceptor: Token rechazado por el servidor (401)")
            runBlocking {
                sesion.clearToken()
            }
        }

        return response
    }
}