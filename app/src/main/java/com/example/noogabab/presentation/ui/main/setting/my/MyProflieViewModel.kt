package com.example.noogabab.presentation.ui.main.setting.my

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.noogabab.data.api.model.ResultData
import com.example.noogabab.data.api.request.CreateUserRequest
import com.example.noogabab.domain.usecase.UserUseCase


class MyProflieViewModel : ViewModel() {
    private val _currentName = MutableLiveData<String>()
    private val _currentRole = MutableLiveData<String>()
    private val _currentBtnState = MutableLiveData<Boolean>()

    init {
        _currentBtnState.value = false
    }

    val currentBtnState: LiveData<Boolean>
        get() = _currentBtnState

//    fun createUser(key: String) = liveData {
//        emit(ResultData.Loading())
//        val user = CreateUserRequest(_currentName.value, _currentRole.value)
//        emit(useCase.createUser(key, user))
//    }

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