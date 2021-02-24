package com.example.noogabab.data.api.model

import com.google.gson.annotations.SerializedName

data class GetDogModel(
    @SerializedName("data")
    val dogData: DogData?,
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean
)
