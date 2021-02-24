package com.example.noogabab.data.api.model


import com.google.gson.annotations.SerializedName

data class CreateUserModel(
    @SerializedName("data")
    val createUserData: CreateUserData?,
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean
)