package com.mvvm.examplearchitecture.repository

import com.example.agentmate.repository.Either
import com.mvvm.examplearchitecture.tempmodels.LoginRequestModel
import com.mvvm.examplearchitecture.tempmodels.LoginResponse
import javax.inject.Singleton

@Singleton
interface MyRepository {
    suspend fun callLoginApi(loginRequest: LoginRequestModel): Either<LoginResponse?>
}