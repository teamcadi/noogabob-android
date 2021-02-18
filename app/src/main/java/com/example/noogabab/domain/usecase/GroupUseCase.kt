package com.example.noogabab.domain.usecase

import com.example.noogabab.data.api.model.CreateGroupModel
import com.example.noogabab.data.api.model.ResultData
import com.example.noogabab.data.api.request.CreateGroupRequest
import com.example.noogabab.domain.repository.GroupRepository
import javax.inject.Inject

class GroupUseCase @Inject constructor(private val groupRepository: GroupRepository) {
    suspend fun createGroupAndDog(createGroupRequest: CreateGroupRequest): ResultData<CreateGroupModel> {
        val createGroupRes = groupRepository.createGroup(createGroupRequest)
        return if (createGroupRes.success) ResultData.Success(createGroupRes)
        else ResultData.Failed(createGroupRes.message)
    }
}