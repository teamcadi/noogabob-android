package com.example.noogabab.presentation.ui.main.setting.my

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyProflieViewModel constructor(): ViewModel() {
    private val _currentName = MutableLiveData<String>()
    private val _currentRole = MutableLiveData<String>()
    private val _currentBtnState = MutableLiveData<Boolean>()

    init {
        _currentName.value = ""
        _currentRole.value = ""
        _currentBtnState.value = false
    }

    val currentBtnState: LiveData<Boolean>
        get() = _currentBtnState

    fun updateName(input: String) {
        _currentName.value = input
        isValidation()
    }

    fun updateRole(input: String) {
        _currentRole.value = input
        isValidation()
    }

    private fun isValidation() {
        _currentBtnState.value = _currentName.value != "" && _currentRole.value != ""
    }
}