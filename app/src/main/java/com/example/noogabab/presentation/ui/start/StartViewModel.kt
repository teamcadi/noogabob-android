package com.example.noogabab.presentation.ui.start

import android.util.Log
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

    fun getCurrentKey() = _currentKey.value

    fun updateKey(input: String) {
        _currentKey.value = input
        Log.d("start", "updateKey: ${_currentKey.value}")
        isValidation()
    }

    private fun isValidation() {
        _currentBtnState.value = _currentKey.value != ""
    }
}