package com.example.noogabab.data.api

import com.example.noogabab.data.api.model.TimelineModel
import com.example.noogabab.util.NetworkConstants
import retrofit2.http.*

interface ApiService {
    // groups
    @FormUrlEncoded
    @POST(NetworkConstants.URL_GROUPS)
    suspend fun createGroup(
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
    @FormUrlEncoded
    @POST(NetworkConstants.URL_USERS)
    suspend fun createUser()

    @GET(NetworkConstants.URL_USERS_USER)
    suspend fun getUser()

    @GET(NetworkConstants.URL_USERS_USER)
    suspend fun updateUser()

    @DELETE(NetworkConstants.URL_USERS_USER)
    suspend fun deleteUser()

    // dogs
    @FormUrlEncoded
    @PUT(NetworkConstants.URL_DOGS_DOG)
    suspend fun modifyDog()

    @FormUrlEncoded
    @POST(NetworkConstants.URL_DOGS_MEAL)
    suspend fun feedMealDog()

    @FormUrlEncoded
    @POST(NetworkConstants.URL_DOGS_SNACK)
    suspend fun feedSnackDog()
}