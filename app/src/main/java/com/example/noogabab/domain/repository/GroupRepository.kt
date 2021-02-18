package com.example.noogabab.domain.repository

import com.example.noogabab.data.api.ApiService
import com.example.noogabab.data.api.request.CreateGroupRequest
import javax.inject.Inject

class GroupRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun createGroup(createGroupRequest: CreateGroupRequest) = apiService.createGroup(createGroupRequest)
//    suspend fun getGroup(groupId: Int) = apiService.getGroup(groupId)
}