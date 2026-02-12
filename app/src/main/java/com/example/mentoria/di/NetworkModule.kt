package com.example.mentoria.di

import com.example.mentoria.core.data.remote.MentoriaApiService
import com.example.mentoria.core.network.BaseUrlProvider
import com.example.mentoria.core.network.json
import com.example.mentoria.features.auth.data.remote.AuthApi
import com.example.mentoria.features.auth.data.remote.AuthInterceptor
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

// TODO: seguramente haya que borrar
object NetworkModule {

    const val BASE_URL =
        "http://localhost:8080" // ""https://ktor-todos.fly.dev/" //  "http://192.168.1.8:8080/" //"https://my-json-server.typicode.com/rafapuig/PMDM-Android-Compose/"

    private val json = Json {
        ignoreUnknownKeys = true
        explicitNulls = false
        isLenient = true
    }

    private val contentType = "application/json;charset=UTF-8".toMediaType()


    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(json.asConverterFactory(contentType))
        .build()


    val apiService: MentoriaApiService by lazy {
        retrofit.create(MentoriaApiService::class.java)
    }

}

val networkModule = module {
    single { json }

    singleOf(::AuthInterceptor)

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<AuthInterceptor>())
            .build()
    }

    single {
        // Para acceder a tu PC (donde corre la API), usa "10.0.2.2"
        // 192.168.56.1
        Retrofit.Builder()
            //.baseUrl(get<BaseUrlProvider>().baseUrl())
            .baseUrl("http://192.168.56.1:8080") // TODO: poner direccion a la api
            .client(get())
            .addConverterFactory(
                Json.asConverterFactory(
                    "application/json".toMediaType()
                )
            )
            .build()
    }
}
    /*
    single<AuthApi> {
        get<Retrofit>().create(AuthApi::class.java)
    }
     */



