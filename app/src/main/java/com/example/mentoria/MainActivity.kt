package com.example.mentoria

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import com.example.mentoria.navigation.NavigationRoot
import com.example.mentoria.ui.theme.MentoriaTheme
import com.example.mentoria.core.presentation.screens.MainViewModel
import com.example.mentoria.navigation.HomeKey
import com.example.mentoria.navigation.LoginKey
import org.koin.androidx.viewmodel.ext.android.viewModel
//import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModel()

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
            MentoriaTheme {
                /*
                val state by viewModel.uiState.collectAsStateWithLifecycle()
                if (state != StartUiState.Loading) {
                    val startDestination = when(state) {
                        StartUiState.Logged -> HomeKey
                        StartUiState.NotLogged -> LoginKey
                        else -> error("Estado inválido")
                    }
                }*/
                NavigationRoot(startDestination = LoginKey)
            }
        }
    }
}