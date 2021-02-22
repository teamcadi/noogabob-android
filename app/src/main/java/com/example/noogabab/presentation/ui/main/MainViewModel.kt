package com.example.noogabab.presentation.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.noogabab.data.api.model.ResultData
import com.example.noogabab.domain.usecase.GroupUseCase
import com.example.noogabab.presentation.entity.PresenterAlbumImage

class MainViewModel @ViewModelInject constructor(private val groupUseCase: GroupUseCase) : ViewModel() {
    private val _currentDogProfile = MutableLiveData<PresenterAlbumImage?>()
    private val _currentDogName = MutableLiveData<String>()
    private val _currentLatestTimeline = MutableLiveData<String>()

    init {
        _currentDogName.value = ""
        _currentLatestTimeline.value= "로딩 중"
        _currentDogProfile.value = null
    }

    fun getChart(key: String, groupId: Int, type: String, date: String) =
         liveData {
            emit(ResultData.Loading())
            emit(groupUseCase.getGroupStatistics(key, groupId, type, date))
        }
}


