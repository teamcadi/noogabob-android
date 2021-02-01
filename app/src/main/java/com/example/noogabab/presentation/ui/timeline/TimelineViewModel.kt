package com.example.noogabab.presentation.ui.timeline

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.noogabab.data.model.ResultData
import com.example.noogabab.domain.usecase.TimelineUseCase

class TimelineViewModel @ViewModelInject constructor(private val useCase: TimelineUseCase) :
    ViewModel() {

    fun getTimeline() = getTimelineList()

    private fun getTimelineList() =
        liveData {
            emit(ResultData.Loading())
            emit(useCase.getTimelineList(1))
        }
}