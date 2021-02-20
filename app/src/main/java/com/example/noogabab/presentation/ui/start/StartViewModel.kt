package com.example.noogabab.presentation.ui.start

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.noogabab.domain.usecase.DogUseCase

class StartViewModel @ViewModelInject constructor(private val useCase: DogUseCase) : ViewModel() {
    private val _currentKey = MutableLiveData<String>()
    private val _currentBtnState = MutableLiveData<Boolean>()

    init {
        _currentKey.value = ""
        _currentBtnState.value = false
    }

    val currentBtnState: LiveData<Boolean>
        get() = _currentBtnState

    fun getDog() = liveData {
        emit(useCase.getDog(_currentKey.value!!))
    }

    fun getCurrentKey() = _currentKey.value

    fun updateKey(input: String) {
        _currentKey.value = input
        isValidation()
    }

    private fun isValidation() {
        _currentBtnState.value = _currentKey.value != ""
    }
}