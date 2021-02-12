package com.example.noogabab.presentation.ui.main.timeline

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.noogabab.data.api.model.ResultData
import com.example.noogabab.domain.usecase.TimelineUseCase

class TimelineViewModel @ViewModelInject constructor(private val useCase: TimelineUseCase) :
    ViewModel() {

    fun getTimeline() = getTimelineList()

    private fun getTimelineList() =
        liveData {
            emit(ResultData.Loading())
            // todo: 그룹 아이디
            emit(useCase.getTimelineList(1))
        }
}