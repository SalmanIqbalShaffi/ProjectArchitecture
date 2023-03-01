package com.mvvm.examplearchitecture.repository

import android.content.Context
import com.example.agentmate.repository.Either
import com.mvvm.examplearchitecture.apiInterface.MyApiInterface
import com.mvvm.examplearchitecture.tempmodels.LoginRequestModel
import com.mvvm.examplearchitecture.tempmodels.LoginResponse
import timber.log.Timber
import javax.inject.Inject

class MyRepositoryImp @Inject constructor(
    private val apiInterface: MyApiInterface,
    private val context: Context
): MyRepository{
    override suspend fun callLoginApi(loginRequest: LoginRequestModel): Either<LoginResponse?> {
        try {
            val response = apiInterface.createLoginRequest(loginRequest)
            Timber.tag("LoginRepoResponse").d(response.toString())

            return if (response.isSuccessful) {
                if (response.body()?.responsecode == "200") {
                    Either.success(response.body())
                } else {
                    Either.error(response.body()?.responsemessage.toString())
                }
            } else {
                Either.error(response.body()?.responsemessage.toString())
            }
        } catch (e: Exception) {
            return Either.error("internet nae hi")
        }
    }


}