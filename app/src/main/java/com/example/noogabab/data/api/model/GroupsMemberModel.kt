package com.example.noogabab.data.api.model
import com.google.gson.annotations.SerializedName

data class GroupsMemberModel(
    @SerializedName("data")
    val data: List<User>?,
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean
)