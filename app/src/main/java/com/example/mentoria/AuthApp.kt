package com.example.mentoria

import android.app.Application
import com.example.mentoria.di.appModule
import com.example.mentoria.di.authModule
import com.example.mentoria.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * No olvides registrar la Application en el AndroidManifest.xml 👇
 */
class AuthApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AuthApp)

            modules(
                networkModule,
                appModule)
        }
    }
}
