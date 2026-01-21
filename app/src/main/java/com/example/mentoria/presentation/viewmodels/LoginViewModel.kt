package com.example.mentoria.presentation.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    val isLoading = mutableStateOf(value=false)
    /*
    */
    val imageErrorAuth = mutableStateOf(value = false)
    val progressBar = mutableStateOf(value=false)
    private val loginRequestLiveData = MutableLiveData<Boolean>()

    fun login(user: String, password: String) {
        viewModelScope.launch {
            try {
                progressBar.value = true
                /*
                val authService = RetrofitHelper.getAuthService()
                val responsiveService = authService.getLogin(LoginDto(user = user, password = password))

                if (responseService.isSuccessful) {
                    delay(1500L)
                    isLoading.value = true
                    responseService.body()?.let { tokenDto ->
                        Log.d("Logging", "Response TokenDto:
                            $tokenDto")
                    }
                } else {
                    responseService.errorBody()?.let { error ->
                        imageErrorAuth.value = true
                        delay(1500L)
                        imageErrorAuth.value = false
                        error.close()
                    }
                }
                loginRequestLiveData.postValue(responseService.isSuccessful)
                */
                progressBar.value = false
            } catch (e: Exception) {
                Log.d("Logging", "Error Authentication", e)
                progressBar.value = false
            }
        }
    }
}