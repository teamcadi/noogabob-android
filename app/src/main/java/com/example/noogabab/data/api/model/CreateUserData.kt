package com.example.noogabab.data.api.model


import com.google.gson.annotations.SerializedName

data class CreateUserData(
    @SerializedName("dog")
    val dogData: DogData?,
    @SerializedName("groupId")
    val groupId: Int?,
    @SerializedName("key")
    val key: String?,
    @SerializedName("userId")
    val userId: Int?
)