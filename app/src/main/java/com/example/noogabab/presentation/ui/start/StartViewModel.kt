package com.example.noogabab.presentation.ui.start

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.noogabab.data.api.model.ResultData
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

    private fun isKorean(s: String): Boolean {
        var i = 0
        while (i < s.length) {
            val c = s.codePointAt(i)
            if (c in 0x3131..0x318E) return true
            if (c in 0xAC00..0xD800) return true
            i += Character.charCount(c)
        }
        return false
    }

    fun getDog() =
        liveData {
            emit(ResultData.Loading())
            val key = _currentKey.value!!
            if (isKorean(key)) emit(ResultData.Failed(""))
            else emit(useCase.getDog(key))
        }

    fun getGroupKey() = _currentKey.value

    fun updateKey(input: String) {
        _currentKey.value = input
        isValidation()
    }

    private fun isValidation() {
        _currentBtnState.value = _currentKey.value != ""
    }
}