package com.example.noogabab.data.api

import com.example.noogabab.data.model.TimelineModel
import com.example.noogabab.util.NetworkConstants
import retrofit2.http.GET

interface TimelineService {
    @GET(NetworkConstants.URL_GROUPS_TIMELINE)
    suspend fun getTimeline(): TimelineModel
}