package com.example.noogabab.data.api

import com.example.noogabab.data.dto.TimelineResponse
import retrofit2.http.GET

interface TimelineService {
    @GET("{groupId}/timeline")
    suspend fun getTimeline(): TimelineResponse
}