// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
}

/* Ingresar al URL de la Rest API
buildTypes {
     release { // desplegar la app a la tienda (?)
        minifyEnabled false
        proguardFiles getDefaultProguardFile('proguard-android-
        optimize.txt'), 'proguard-rules.pro'
        buildConfigField "String", "API_BASE_URL",
        "\"https://tasks-planner-api.herokuapp.com/\""
     }
     debug { // para el desarrollo
        initWith debug
        buildConfigField "String", "API_BASE_URL",
        "\"https://tasks-planner-api.herokuapp.com/\""
     }
}
 */