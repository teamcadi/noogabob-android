package com.example.noogabab.util

object NetworkConstants {
    const val BASE_URL = "http://13.125.103.179:9000/api/"

    // groups
    const val URL_GROUPS = "groups"
    const val URL_GROUPS_TIMELINE = "groups/{groupId}/timeline"
    const val URL_GROUPS_MEMBERS = "groups/{groupId}/members"
    const val URL_GROUPS_ALBUMS = "groups/{groupId}/albums"
    const val URL_GROUPS_STATISTICS = "groups/{groupId}/statics"

    // users
    const val URL_USERS = "users"
    const val URL_USERS_USER = "users/{userId}"

    // dogs
    const val URL_DOGS = "dogs"
    const val URL_DOGS_DOG = "dogs/{dogId}"
    const val URL_DOGS_MEAL = "dogs/{dogId}/meals"
    const val URL_DOGS_MEAL_LATEST = "dogs/{dogId}/meal/latest"
    const val URL_DOGS_SNACK = "dogs/{dogId}/snacks"
}