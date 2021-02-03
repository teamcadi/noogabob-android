package com.example.noogabab.domain.usecase

import com.example.noogabab.data.api.model.ResultData
import com.example.noogabab.data.api.model.TimelineModel
import com.example.noogabab.domain.repository.TimelineRepository
import javax.inject.Inject

class TimelineUseCase @Inject constructor(private val timelineRepository: TimelineRepository) {
    suspend fun getTimelineList(groupId: Int): ResultData<TimelineModel> {
        val timeline = timelineRepository.getTimeline(groupId)

        return if (timeline.success) ResultData.Success(timeline)
        else ResultData.Failed(timeline.message)
    }
}