package com.mvvm.examplearchitecture.apiInterface

import com.mvvm.examplearchitecture.tempmodels.LoginRequestModel
import com.mvvm.examplearchitecture.tempmodels.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface MyApiInterface {

    @POST("accountLogin")
    suspend fun createLoginRequest(@Body loginRequest: LoginRequestModel): Response<LoginResponse>
}