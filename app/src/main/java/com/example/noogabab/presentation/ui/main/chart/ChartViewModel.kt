package com.example.noogabab.presentation.ui.main.chart

import android.annotation.SuppressLint
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.noogabab.data.api.model.ResultData
import com.example.noogabab.domain.usecase.GroupUseCase
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

@SuppressLint("SimpleDateFormat")
class ChartViewModel @ViewModelInject constructor(private val groupUseCase: GroupUseCase): ViewModel() {
    private val _currentXGroups = MutableLiveData<ArrayList<String>>()
    private val _currentYBobs = MutableLiveData<FloatArray>()
    private val _currentYSnacks = MutableLiveData<FloatArray>()
    private val _currentDate = MutableLiveData<String>()

    init {
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        _currentXGroups.value = ArrayList<String>()
        _currentYBobs.value = floatArrayOf()
        _currentYSnacks.value = floatArrayOf()
        _currentDate.value = sdf.format(Date(System.currentTimeMillis()))
    }

    fun getStatistics(key: String, groupId: Int, type: String, date: String) = liveData {
        emit(ResultData.Loading())
        emit(groupUseCase.getGroupStatistics(key, groupId, type, date))
    }

    val currentXGroups: LiveData<ArrayList<String>>
        get() = _currentXGroups

    val currentDate: LiveData<String>
        get() = _currentDate

    val currentYBobs: FloatArray?
        get() = _currentYBobs.value

    val currentYSnacks: FloatArray?
        get() = _currentYSnacks.value

    fun updateChart(xGroups: ArrayList<String>, yBobs: FloatArray, ySnacks: FloatArray) {
        _currentXGroups.value = xGroups
        _currentYBobs.value = yBobs
        _currentYSnacks.value = ySnacks
    }

    fun updateDate(input: String) {
        _currentDate.value = input
    }
}