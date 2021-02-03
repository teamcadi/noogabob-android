package com.example.noogabab.data.api.model


import com.google.gson.annotations.SerializedName

data class AlbumModel(
    @SerializedName("data")
    val data: List<Image>?,
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean
)