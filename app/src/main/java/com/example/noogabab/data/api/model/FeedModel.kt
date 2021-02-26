package com.example.noogabab.data.api.model


import com.google.gson.annotations.SerializedName

data class FeedModel(
    @SerializedName("data")
    val data: Any?,
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean
)