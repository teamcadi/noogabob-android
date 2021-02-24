package com.example.noogabab.domain.repository

import com.example.noogabab.data.api.ApiService
import com.example.noogabab.data.api.request.CreateUserRequest
import javax.inject.Inject

class UserRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun createUser(key: String, createUserRequest: CreateUserRequest) =
        apiService.createUser(key, createUserRequest)
}