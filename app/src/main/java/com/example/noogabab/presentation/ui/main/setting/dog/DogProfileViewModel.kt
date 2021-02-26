package com.example.noogabab.presentation.ui.main.setting.dog

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.noogabab.data.api.request.CreateGroupRequest
import com.example.noogabab.domain.usecase.GroupUseCase
import com.example.noogabab.presentation.entity.PresenterBobTime

class DogProfileViewModel @ViewModelInject constructor(private val groupUseCase: GroupUseCase) : ViewModel() {
    private val _currentDogName = MutableLiveData<String>()
    private val _currentDogAge = MutableLiveData<String>()
    private val _currentDogKind = MutableLiveData<String>()
    private val _currentBobTimes = MutableLiveData<ArrayList<PresenterBobTime>>()

    private val _currentBtnState = MutableLiveData<Boolean>()

    init {
        _currentDogName.value = ""
        _currentDogAge.value = "0"
        _currentDogKind.value = ""
        _currentBobTimes.value = ArrayList<PresenterBobTime>()
        _currentBtnState.value = true
    }

//    fun createGroupAndDog() = liveData {
//        val bobTimes = ArrayList<String>()
//        for (i in _currentBobTimes.value!!) bobTimes.add(i.time)
//        val createGroupRequest = CreateGroupRequest(
//            _currentDogName.value,
//            _currentDogAge.value,
//            _currentDogKind.value,
//            bobTimes
//        )
//        emit(useCase.createGroupAndDog(createGroupRequest))
//    }

    val currentBtnState: LiveData<Boolean>
        get() = _currentBtnState

    val currentDogKind: LiveData<String>
        get() = _currentDogKind

    val getDogName: String?
        get() = _currentDogName.value

    val getDogAge: String?
        get() = _currentDogAge.value

    val getDogKind: String?
        get() = _currentDogKind.value

    fun updateDogName(input: String) {
        _currentDogName.value = input
        isValidation()
    }

    fun updateDogAge(input: String) {
        _currentDogAge.value = input
        isValidation()
    }

    fun updateDogKind(input: String) {
        _currentDogKind.value = input
        isValidation()
    }

    fun updateBobTimes(input: PresenterBobTime) {
        _currentBobTimes.value?.add(input)
        isValidation()
    }

    private fun isValidation() {
        _currentBtnState.value =
            !(_currentDogName.value == "" || _currentDogAge.value == "0" ||
                    _currentDogKind.value == "" )
    }
}