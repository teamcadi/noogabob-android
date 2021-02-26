package com.example.noogabab.domain.repository

import com.example.noogabab.data.api.ApiService
import com.example.noogabab.data.api.request.FeedRequest
import okhttp3.Headers
import javax.inject.Inject

class DogRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getDog(key: String) = apiService.getDog(key)
    suspend fun getMealLatest(dogId: Int) = apiService.getMealLatest(dogId)
    suspend fun feedMeal(key: String, dogId: Int, feed: FeedRequest) = apiService.feedMealDog(key, dogId, feed)
    suspend fun feedSnack(key: String, dogId: Int, feed: FeedRequest) = apiService.feedSnackDog(key, dogId, feed)
}