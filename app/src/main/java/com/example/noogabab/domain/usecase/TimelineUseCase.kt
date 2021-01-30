package com.example.noogabab.domain.usecase

import com.example.noogabab.data.model.ResultData
import com.example.noogabab.data.model.TimelineModel
import com.example.noogabab.domain.repository.TimelineRepository
import javax.inject.Inject

class TimelineUseCase @Inject constructor(private val timelineRepository: TimelineRepository) {
    suspend fun getTimelineList(): ResultData<TimelineModel> {
        val timeline = timelineRepository.getTimeline()

        return if (timeline.success) ResultData.Success(timeline)
        else ResultData.Failed("문제가 있대")
    }
}