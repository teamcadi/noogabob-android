package com.example.noogabab.domain.repository

import com.example.noogabab.data.api.ApiService
import okhttp3.Headers
import javax.inject.Inject

class DogRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getDog(key: String) = apiService.getDog(key)
}