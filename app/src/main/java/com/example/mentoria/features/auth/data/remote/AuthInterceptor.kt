package com.example.mentoria.features.auth.data.remote

import com.example.mentoria.features.auth.data.local.AuthLocalDataSource
import com.example.mentoria.features.auth.domain.usecases.LogoutUseCase
import com.example.mentoria.features.auth.data.local.SessionManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val local: AuthLocalDataSource,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = runBlocking {
            local.getToken()
        }
        val request = chain.request().newBuilder()
            .apply {
                token?.let{
                    addHeader("Authorization", "Bearer $it")
                }
            }.build()

        val response = chain.proceed(request)

        if (response.code == 401) {
            runBlocking {
                local.clearToken()
            }
        }

        return response
    }
}