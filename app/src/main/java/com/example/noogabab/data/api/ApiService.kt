package com.example.noogabab.data.api

import com.example.noogabab.data.api.model.CreateGroupModel
import com.example.noogabab.data.api.model.CreateUserModel
import com.example.noogabab.data.api.model.GetDogModel
import com.example.noogabab.data.api.request.CreateGroupRequest
import com.example.noogabab.data.api.model.TimelineModel
import com.example.noogabab.data.api.request.CreateUserRequest
import com.example.noogabab.util.NetworkConstants
import retrofit2.http.*

interface ApiService {
    // groups
    @POST(NetworkConstants.URL_GROUPS)
    suspend fun createGroup(@Body createGroupRequest: CreateGroupRequest): CreateGroupModel

    @GET(NetworkConstants.URL_GROUPS)
    suspend fun getGroup(
    )

    @GET(NetworkConstants.URL_GROUPS_MEMBERS)
    suspend fun getMembers()

    @GET(NetworkConstants.URL_GROUPS_STATISTICS)
    suspend fun getStatistics()

    @GET(NetworkConstants.URL_GROUPS_ALBUMS)
    suspend fun getImages()

    @FormUrlEncoded
    @POST(NetworkConstants.URL_GROUPS_ALBUMS)
    suspend fun uploadImage()

    @GET(NetworkConstants.URL_GROUPS_TIMELINE)
    suspend fun getTimeline(@Path("groupId") groupId: Int): TimelineModel

    // users
    @POST(NetworkConstants.URL_USERS)
    suspend fun createUser(
        @Body createUserRequest: CreateUserRequest,
        @Header("key") key: String
    ): CreateUserModel

    @GET(NetworkConstants.URL_USERS_USER)
    suspend fun getUser()

    @GET(NetworkConstants.URL_USERS_USER)
    suspend fun updateUser()

    @DELETE(NetworkConstants.URL_USERS_USER)
    suspend fun deleteUser()

    // dogs
    @GET(NetworkConstants.URL_DOGS_DOG)
    suspend fun getDog(@Header("key") key: String): GetDogModel

    @PUT(NetworkConstants.URL_DOGS_DOG)
    suspend fun modifyDog()

    @POST(NetworkConstants.URL_DOGS_MEAL)
    suspend fun feedMealDog()

    @POST(NetworkConstants.URL_DOGS_SNACK)
    suspend fun feedSnackDog()
}