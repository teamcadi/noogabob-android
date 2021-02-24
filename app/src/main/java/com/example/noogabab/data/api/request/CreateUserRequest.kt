package com.example.noogabab.data.api.request

import com.google.gson.annotations.SerializedName

data class CreateUserRequest(
    @SerializedName("name")
    val name: String?,
    @SerializedName("role")
    val role: String?
)
