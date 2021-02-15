package com.example.noogabab.presentation.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.noogabab.presentation.entity.PresenterAlbumImage

class MainViewModel constructor() : ViewModel() {
    private val _currentDogProfile = MutableLiveData<PresenterAlbumImage?>()
    private val _test = MutableLiveData<String>()

    init {
        _test.value = ""
        _currentDogProfile.value = null
    }

    val currentDogProfile: LiveData<PresenterAlbumImage?>
        get() = _currentDogProfile

    val test: LiveData<String>
        get() = _test

    fun updateTest(input: String) {
        _test.value = input
        Log.d("zzz", "updateTest: ${_test.value}")
    }

    fun updateDogProfile(input: PresenterAlbumImage) {
        Log.d("zzz", "updateDogProfile: ")
        _currentDogProfile.value = input
        Log.d("zzz", "updateDogProfile: ${_currentDogProfile.value}")
    }
}