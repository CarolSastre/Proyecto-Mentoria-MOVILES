package com.example.mentoria.di

import androidx.room.Room
import com.example.mentoria.core.data.local.AppDatabase
import com.example.mentoria.features.auth.data.remote.AuthInterceptor
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

val networkModule = module {
    // 1. configuración JSON
    single {
        Json {
            ignoreUnknownKeys = true
            explicitNulls = false
            isLenient = true
        }
    }

    // 2. Interceptor
    singleOf(::AuthInterceptor)

    // 3. OkHttpClient
    single {
        OkHttpClient.Builder()
            .addInterceptor(get<AuthInterceptor>())
            .build()
    }

    // 4. Instancia de retrofit
    single {
        // IMPORTANTE:
        // Usa "http://10.0.2.2:8080/" si usas el Emulador de Android.
        // Usa "http://TU_IP_LOCAL:8080/" si usas un móvil físico. // ? 10.161.63.163 // 192.168.0.110
        val baseUrl = "http://192.168.0.110:8080/"
        val contentType = "application/json".toMediaType()

        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(get()).addConverterFactory(get<Json>().asConverterFactory(contentType))
            .build()
    }
}


