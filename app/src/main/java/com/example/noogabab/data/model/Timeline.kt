package com.example.noogabab.data.model


import com.google.gson.annotations.SerializedName

data class Timeline(
    @SerializedName("content")
    val content: String,
    @SerializedName("subContent")
    val subContent: String,
    @SerializedName("time")
    val time: Long,
    @SerializedName("type")
    val type: Int
)