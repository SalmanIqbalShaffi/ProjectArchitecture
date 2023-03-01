package com.mvvm.examplearchitecture.tempmodels

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("responsecode")
    val responsecode: String,
    @SerializedName("responsemessage")
    val responsemessage: String,
    @SerializedName("userName")
    val userName: String,
    @SerializedName("userpas")
    val userpas: String
)
