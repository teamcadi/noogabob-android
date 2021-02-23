package com.example.noogabab.domain.usecase

import com.example.noogabab.data.api.model.CreateUserModel
import com.example.noogabab.data.api.model.ResultData
import com.example.noogabab.data.api.request.CreateUserRequest
import com.example.noogabab.domain.repository.UserRepository
import javax.inject.Inject

class UserUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend fun createUser(key: String, createUserRequest: CreateUserRequest): ResultData<CreateUserModel> {
        val user = userRepository.createUser(key, createUserRequest)
        return if (user.success) ResultData.Success(user)
        else ResultData.Failed(user.message)
    }
}