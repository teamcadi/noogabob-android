package com.example.noogabab.presentation.ui.main.chart

import android.annotation.SuppressLint
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.noogabab.data.api.model.ResultData
import com.example.noogabab.domain.usecase.GroupUseCase
import kotlin.collections.ArrayList

class ChartViewModel @ViewModelInject constructor(private val groupUseCase: GroupUseCase): ViewModel() {
    private val _currentWeekXGroups = MutableLiveData<ArrayList<String>>()
    private val _currentWeekYBobs = MutableLiveData<FloatArray>()
    private val _currentWeekYSnacks = MutableLiveData<FloatArray>()
    private val _currentMonthXGroups = MutableLiveData<ArrayList<String>>()
    private val _currentMonthYBobs = MutableLiveData<FloatArray>()
    private val _currentMonthYSnacks = MutableLiveData<FloatArray>()
    private val _currentWeekDate = MutableLiveData<String>()
    private val _currentMonthDate = MutableLiveData<String>()

    init {
        _currentWeekXGroups.value = ArrayList<String>()
        _currentWeekYBobs.value = floatArrayOf()
        _currentWeekYSnacks.value = floatArrayOf()
        _currentMonthXGroups.value = ArrayList<String>()
        _currentMonthYBobs.value = floatArrayOf()
        _currentMonthYSnacks.value = floatArrayOf()
        _currentWeekDate.value = "1970-12-10"
        _currentMonthDate.value = "1970-12-10"
    }

    fun getStatistics(key: String, groupId: Int, type: String, date: String) = liveData {
        emit(ResultData.Loading())
        emit(groupUseCase.getGroupStatistics(key, groupId, type, date))
    }

    val currentWeekXGroups: LiveData<ArrayList<String>>
        get() = _currentWeekXGroups

    val currentWeekDate: LiveData<String>
        get() = _currentWeekDate

    val currentWeekYBobs: FloatArray?
        get() = _currentWeekYBobs.value

    val currentWeekYSnacks: FloatArray?
        get() = _currentWeekYSnacks.value

    val currentMonthXGroups: LiveData<ArrayList<String>>
        get() = _currentMonthXGroups

    val currentMonthDate: LiveData<String>
        get() = _currentMonthDate

    val currentMonthYBobs: FloatArray?
        get() = _currentMonthYBobs.value

    val currentMonthYSnacks: FloatArray?
        get() = _currentMonthYSnacks.value

    fun updateChart(xGroups: ArrayList<String>, yBobs: FloatArray, ySnacks: FloatArray, type: String) {
        when(type) {
            "week" -> {
                _currentWeekYBobs.value = yBobs
                _currentWeekYSnacks.value = ySnacks
                _currentWeekXGroups.value = xGroups // 마지막에 위치시킴
            }
            "month" -> {
                _currentMonthYBobs.value = yBobs
                _currentMonthYSnacks.value = ySnacks
                _currentMonthXGroups.value = xGroups
            }
        }

    }

    fun updateDate(input: String, type: String) {
        if (type == "week") _currentWeekDate.value = input
        else if (type == "month") _currentMonthDate.value = input
    }
}