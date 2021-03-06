package com.example.noogabab.domain.usecase

import com.example.noogabab.data.api.model.FeedModel
import com.example.noogabab.data.api.model.GetDogModel
import com.example.noogabab.data.api.model.MealLatestModel
import com.example.noogabab.data.api.model.ResultData
import com.example.noogabab.data.api.request.FeedRequest
import com.example.noogabab.domain.repository.DogRepository
import javax.inject.Inject

class DogUseCase @Inject constructor(private val dogRepository: DogRepository) {
    suspend fun getDog(key: String): ResultData<GetDogModel> {
        val dog = dogRepository.getDog(key)
        return if (dog.success) ResultData.Success(dog)
        else ResultData.Failed(dog.message)
    }
    suspend fun getMealLatest(dogId: Int): ResultData<MealLatestModel> {
        val mealLatest = dogRepository.getMealLatest(dogId)
        return if (mealLatest.success) ResultData.Success(mealLatest)
        else ResultData.Failed(mealLatest.message)
    }
    suspend fun feedMeal(key: String, dogId: Int, feed: FeedRequest): ResultData<FeedModel> {
        val res = dogRepository.feedMeal(key, dogId, feed)
        return if (res.success) ResultData.Success(res)
        else ResultData.Failed(res.message)
    }
    suspend fun feedSnack(key: String, dogId: Int, feed: FeedRequest): ResultData<FeedModel> {
        val res = dogRepository.feedSnack(key, dogId, feed)
        return if (res.success) ResultData.Success(res)
        else ResultData.Failed(res.message)
    }
}