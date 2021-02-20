package com.example.noogabab.presentation.ui.start.enterGroup

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.noogabab.domain.usecase.DogUseCase
import com.example.noogabab.presentation.entity.PresenterDog

class EnterGroupViewModel @ViewModelInject constructor(private val useCase: DogUseCase): ViewModel() {
    private val _currentName = MutableLiveData<String>()
    private val _currentRole = MutableLiveData<String>()
    private val _currentBtnState = MutableLiveData<Boolean>()

    init {
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