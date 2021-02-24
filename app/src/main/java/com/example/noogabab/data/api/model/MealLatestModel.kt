package com.example.noogabab.data.api.model


import com.google.gson.annotations.SerializedName

data class MealLatestModel(
    @SerializedName("data")
    val content: String?,
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean
)