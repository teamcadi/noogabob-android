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
    private val _currentDog = MutableLiveData<PresenterDog>()

    init {
        _currentName.value = ""
        _currentRole.value = ""
        _currentBtnState.value = false
        _currentDog.value = PresenterDog("불러오는 중", 0, "멍멍이", null)
    }

    fun getDog(key: String) = liveData {
        emit(useCase.getDog(key))
    }

    val currentDog: LiveData<PresenterDog>
        get() = _currentDog

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

    fun updateDog(input: PresenterDog) {
        _currentDog.value = input
    }

    private fun isValidation() {
        _currentBtnState.value = _currentName.value != "" && _currentRole.value != ""
    }
}