package com.example.noogabab.data.api.model


import com.google.gson.annotations.SerializedName

data class StatisticsData(
    @SerializedName("mealRank")
    val mealRankData: List<MealRankData>?,
    @SerializedName("snackRank")
    val snackRankData: List<SnackRankData>?
)