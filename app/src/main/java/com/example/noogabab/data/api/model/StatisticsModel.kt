package com.example.noogabab.data.api.model


import com.google.gson.annotations.SerializedName

data class StatisticsModel(
    @SerializedName("data")
    val statisticsData: StatisticsData?,
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean
)