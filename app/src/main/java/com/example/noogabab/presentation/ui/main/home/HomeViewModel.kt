package com.example.noogabab.presentation.ui.main.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.noogabab.data.api.model.ResultData
import com.example.noogabab.domain.usecase.DogUseCase

class HomeViewModel @ViewModelInject constructor(private val useCase: DogUseCase): ViewModel() {
    private val _currentMealLatest = MutableLiveData<String>()

    init {
        _currentMealLatest.value = "불러오는 중"
    }

    val currentMealLatest: LiveData<String>
        get() = _currentMealLatest

    fun getMealLatest(dogId: Int) =
        liveData {
            emit(ResultData.Loading())
            emit(useCase.getMealLatest(dogId))
        }

    fun updateMealLatest(input: String) {
        _currentMealLatest.value = "최근 식사 $input"
    }
}