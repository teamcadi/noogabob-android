package com.example.noogabab.presentation.ui.main.timeline

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.noogabab.data.api.model.ResultData
import com.example.noogabab.domain.usecase.TimelineUseCase

class TimelineViewModel @ViewModelInject constructor(private val useCase: TimelineUseCase) :
    ViewModel() {

    fun getTimeline(key: String, groupId: Int) =
        liveData {
            emit(ResultData.Loading())
            emit(useCase.getTimelineList(key, groupId))
        }
}