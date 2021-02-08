package com.example.noogabab.data.api.model
import com.google.gson.annotations.SerializedName

data class CreateGroupModel(
    @SerializedName("data")
    val data: String?,
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean
)