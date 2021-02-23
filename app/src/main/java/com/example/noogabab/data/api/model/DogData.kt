package com.example.noogabab.data.api.model


import com.google.gson.annotations.SerializedName

data class DogData(
    @SerializedName("id")
    val dogId: Int?,
    @SerializedName("age")
    val age: Int?,
    @SerializedName("kind")
    val kind: String?,
    @SerializedName("meals")
    val meals: ArrayList<String>?,
    @SerializedName("name")
    val name: String?
)