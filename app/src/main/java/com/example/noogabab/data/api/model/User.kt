package com.example.noogabab.data.api.model
import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("name")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("role")
    val role: String
)