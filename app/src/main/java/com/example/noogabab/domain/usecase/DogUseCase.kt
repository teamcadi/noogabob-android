package com.example.noogabab.domain.usecase

import com.example.noogabab.data.api.model.GetDogModel
import com.example.noogabab.data.api.model.ResultData
import com.example.noogabab.domain.repository.DogRepository
import javax.inject.Inject

class DogUseCase @Inject constructor(private val dogRepository: DogRepository) {
    suspend fun getDog(key: String): ResultData<GetDogModel> {
        val dog = dogRepository.getDog(key)
        return if (dog.success) ResultData.Success(dog)
        else ResultData.Failed(dog.message)
    }
}