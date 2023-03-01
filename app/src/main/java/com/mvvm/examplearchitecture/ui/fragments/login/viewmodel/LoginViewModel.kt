package com.mvvm.examplearchitecture.ui.fragments.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agentmate.repository.ErrorWrapper
import com.mvvm.examplearchitecture.tempmodels.LoginRequestModel
import com.mvvm.examplearchitecture.tempmodels.LoginResponse
import com.mvvm.examplearchitecture.repository.MyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val myRepository: MyRepository) : ViewModel() {

    val error: MutableLiveData<ErrorWrapper> by lazy {
        MutableLiveData<ErrorWrapper>()
    }

    private var _loginResponse = MutableLiveData<LoginResponse>()

    var loginResponse: LiveData<LoginResponse> = _loginResponse

    fun callLoginRequest(loginRequest: LoginRequestModel) {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                val response = myRepository.callLoginApi(loginRequest)
                response.onSuccess {
                    _loginResponse.value = it
                }.onFailure {
                    error.value = ErrorWrapper(it)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

}