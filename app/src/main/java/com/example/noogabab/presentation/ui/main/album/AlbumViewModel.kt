package com.example.noogabab.presentation.ui.main.album

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.noogabab.presentation.entity.PresenterBobTime

class AlbumViewModel: ViewModel() {
    // value
    private val _currentDogName = MutableLiveData<String>()

    init {
        _currentDogName.value = ""
    }

    fun updateDogName(input: String) {
        _currentDogName.value = input
    }
}