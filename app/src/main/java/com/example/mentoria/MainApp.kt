package com.example.mentoria

import android.app.Application
import com.example.mentoria.core.data.remote.mockNetworkModule
import com.example.mentoria.di.appModule
import com.example.mentoria.di.authRepositoryModule
import com.example.mentoria.di.commonAuthModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApp : Application() { // TODO: recordar modificar AndroidManifest.xml
    override fun onCreate() {
        super.onCreate()

        // Inicializar KOIN
        startKoin {
            androidContext(this@MainApp)

            modules(
                mockNetworkModule,
                authRepositoryModule,
                commonAuthModule,
                appModule
            )

            /*
            modules(
                commonAuthModule
            )
             */
        }
    }
}

/** Con mock
startKoin {
androidContext(this@MainApp)

modules(backendModules)
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