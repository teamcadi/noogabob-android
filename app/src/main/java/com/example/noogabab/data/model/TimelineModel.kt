package com.example.noogabab.data.model


import com.google.gson.annotations.SerializedName

data class TimelineModel(
    @SerializedName("data")
    val data: List<Timeline>,
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean
)