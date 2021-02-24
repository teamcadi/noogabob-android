package com.example.noogabab.data.api

import com.example.noogabab.data.api.model.*
import com.example.noogabab.data.api.request.CreateGroupRequest
import com.example.noogabab.data.api.request.CreateUserRequest
import com.example.noogabab.util.NetworkConstants
import okhttp3.RequestBody
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
    suspend fun getStatistics(
        @Header("key") key: String,
        @Path("groupId") groupId: Int,
        @Query("type") type: String,
        @Query("date") date: String
    ): StatisticsModel

    @GET(NetworkConstants.URL_GROUPS_ALBUMS)
    suspend fun getImages()

    @Multipart
    @POST(NetworkConstants.URL_GROUPS_ALBUMS)
    @Headers("Content-Type: image/*")
    suspend fun uploadImage(@Part("image") request: RequestBody)

    @GET(NetworkConstants.URL_GROUPS_TIMELINE)
    suspend fun getTimeline(
        @Header("key") key: String,
        @Path("groupId") groupId: Int
    ): TimelineModel

    // users
    @POST(NetworkConstants.URL_USERS)
    suspend fun createUser(
        @Header("key") key: String,
        @Body createUserRequest: CreateUserRequest
    ): CreateUserModel

    @GET(NetworkConstants.URL_USERS_USER)
    suspend fun getUser()

    @GET(NetworkConstants.URL_USERS_USER)
    suspend fun updateUser()

    @DELETE(NetworkConstants.URL_USERS_USER)
    suspend fun deleteUser()

    // dogs
    @GET(NetworkConstants.URL_DOGS)
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    suspend fun getDog(@Header("key") key: String): GetDogModel

    @GET(NetworkConstants.URL_DOGS_MEAL_LATEST)
    suspend fun getMealLatest(@Path("dogId") dogId: Int): MealLatestModel

    @PUT(NetworkConstants.URL_DOGS_DOG)
    suspend fun modifyDog()

    @POST(NetworkConstants.URL_DOGS_MEAL)
    suspend fun feedMealDog()

    @POST(NetworkConstants.URL_DOGS_SNACK)
    suspend fun feedSnackDog()
}