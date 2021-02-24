package com.example.noogabab.data.api.model
import com.google.gson.annotations.SerializedName

data class TimelineData(
    @SerializedName("content")
    val content: String,
    @SerializedName("subContent")
    val subContent: String,
    @SerializedName("time")
    val time: Long,
    @SerializedName("type")
    val type: Int
)