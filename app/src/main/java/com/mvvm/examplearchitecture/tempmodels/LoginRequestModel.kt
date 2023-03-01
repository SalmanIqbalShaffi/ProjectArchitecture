package com.mvvm.examplearchitecture.tempmodels

import com.google.gson.annotations.SerializedName

data class LoginRequestModel(
    @SerializedName("password")
    val password: String,
    @SerializedName("userName")
    val userName: String
)
