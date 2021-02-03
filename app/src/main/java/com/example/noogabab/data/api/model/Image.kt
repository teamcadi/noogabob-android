package com.example.noogabab.data.api.model


import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("id")
    val id: Int,
    @SerializedName("album")
    val album: String
)