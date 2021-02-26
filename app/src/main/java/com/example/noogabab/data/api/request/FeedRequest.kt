package com.example.noogabab.data.api.request

import com.google.gson.annotations.SerializedName

data class FeedRequest(
    @SerializedName("userId")
    val userId: Int,
)
