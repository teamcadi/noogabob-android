package com.example.noogabab.presentation.ui.start

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StartViewModel constructor() : ViewModel() {
    private val _currentKey = MutableLiveData<String>()
    private val _currentBtnState = MutableLiveData<Boolean>()

    init {
        _currentKey.value = ""
        _currentBtnState.value = false
    }

    val currentBtnState: LiveData<Boolean>
        get() = _currentBtnState

    fun updateKey(input: String) {
        _currentKey.value = input
        isValidation()
    }

    private fun isValidation() {
        _currentBtnState.value = _currentKey.value != ""
    }
}