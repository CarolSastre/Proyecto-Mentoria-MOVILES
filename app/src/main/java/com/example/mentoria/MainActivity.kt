package com.example.mentoria

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.mentoria.navigation.NavigationRoot
import com.example.mentoria.ui.theme.Mentoria
import com.example.mentoria.core.presentation.screens.MainViewModel
import com.example.mentoria.core.presentation.screens.home.HomeViewModel
import com.example.mentoria.navigation.HomeKey
//import com.example.mentoria.navigation.LoginKey
import org.koin.androidx.viewmodel.ext.android.viewModel

//import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModel()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        //val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        /*
        splashScreen.setKeepOnScreenCondition {
        viewModel.uiState.value is StartUiState.Loading
        }
         */

        setContent {
            Mentoria {
                /*
                val state by viewModel.uiState.collectAsStateWithLifecycle()
                if (state != StartUiState.Loading) {
                    val startDestination = when(state) {
                        StartUiState.Logged -> HomeKey
                        StartUiState.NotLogged -> LoginKey
                        else -> error("Estado inválido")
                    }
                }*/
                NavigationRoot(startDestination = HomeKey)
            }
        }
    }
}