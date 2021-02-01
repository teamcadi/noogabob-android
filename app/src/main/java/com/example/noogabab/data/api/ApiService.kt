package com.example.noogabab.data.api

import com.example.noogabab.data.model.TimelineModel
import com.example.noogabab.util.NetworkConstants
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET(NetworkConstants.URL_GROUPS_TIMELINE)
    suspend fun getTimeline(@Path("groupId") groupId: Int): TimelineModel
}