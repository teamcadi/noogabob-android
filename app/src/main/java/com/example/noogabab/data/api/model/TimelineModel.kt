package com.example.noogabab.data.api.model
import com.google.gson.annotations.SerializedName

data class TimelineModel(
    @SerializedName("data")
    val data: ArrayList<TimelineData>?,
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean
)