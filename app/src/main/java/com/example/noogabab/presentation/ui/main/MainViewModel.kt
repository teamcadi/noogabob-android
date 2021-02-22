package com.example.noogabab.presentation.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.noogabab.presentation.entity.PresenterAlbumImage

class MainViewModel constructor() : ViewModel() {
    private val _currentDogProfile = MutableLiveData<PresenterAlbumImage?>()
    private val _currentDogName = MutableLiveData<String>()
    private val _currentLatestTimeline = MutableLiveData<String>()

    init {
        _currentDogName.value = ""
        _currentLatestTimeline.value= "로딩 중"
        _currentDogProfile.value = null
    }

    val currentDogProfile: LiveData<PresenterAlbumImage?>
        get() = _currentDogProfile
}