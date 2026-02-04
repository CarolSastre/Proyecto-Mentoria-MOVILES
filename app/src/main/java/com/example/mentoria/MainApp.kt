package com.example.mentoria

import android.app.Application
import com.example.mentoria.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApp : Application() { // TODO: recordar modificar AndroidManifest.xml
    override fun onCreate() {
        super.onCreate()

        // Inicializar KOIN
        startKoin {
            androidContext(this@MainApp)

            //modules(commonAuthModule)
            modules(appModule)

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