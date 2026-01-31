package com.example.mentoria

import android.app.Application
import com.example.mentoria.di.appModule
import com.example.mentoria.di.commonAuthModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


/**
 * No olvides registrar la Application en el AndroidManifest.xml 👇
 */
class AuthApp : Application() {

    override fun onCreate() {
        super.onCreate()

        /** 🚀 Inicializar Koin */
        startKoin {
            androidContext(this@AuthApp)

            modules(commonAuthModule)
            modules(appModule)

            /*when (BuildConfig.BUILD_TYPE) {
                "staging" ->
                    modules(
                        dataStoreModule,
                        fakeNetworkModule,
                        authRepositoryModule,
                        commonAuthModule
                    )

                "debug" ->
                    modules(
                        fakeAuthRepositoryModule,
                        commonAuthModule
                    )

                else ->
                    modules(
                        dataStoreModule,
                        networkModule,
                        authRepositoryModule,
                        commonAuthModule
                    )
            }*/
        }
    }
}
