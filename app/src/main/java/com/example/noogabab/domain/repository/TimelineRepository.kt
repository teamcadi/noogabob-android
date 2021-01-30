package com.example.noogabab.domain.repository

import com.example.noogabab.data.api.TimelineService
import javax.inject.Inject

class TimelineRepository @Inject constructor(private val timelineService: TimelineService) {
    suspend fun getTimeline() = timelineService.getTimeline()

}