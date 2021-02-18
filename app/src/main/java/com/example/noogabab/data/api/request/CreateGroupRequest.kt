package com.example.noogabab.data.api.request

import com.google.gson.annotations.SerializedName

data class CreateGroupRequest(
    @SerializedName("name")
    val name: String?,
    @SerializedName("age")
    val age: Int?,
    @SerializedName("kind")
    val kind: String?,
    @SerializedName("meals")
    val meals: List<String>?
)