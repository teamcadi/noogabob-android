package com.example.noogabab.data.api.model


import com.google.gson.annotations.SerializedName

data class CreateGroupData(
    @SerializedName("dogId")
    val dogId: Int?,
    @SerializedName("groupId")
    val groupId: Int?,
    @SerializedName("key")
    val key: String?
)