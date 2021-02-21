package com.example.noogabab.util

object SharedProfile {
    const val NAME = "profile"
    const val IMAGE_KEY = "profile_image"
}

object SharedGroup {
    const val NAME = "group"
    const val GROUP_UUID_KEY = "group_uuid"
    const val GROUP_ID_KEY = "group_id"
}

object SharedUser {
    const val NAME = "user"
    const val USER_ID_KEY = "user_id"
}

object SharedDog {
    const val NAME = "dog"
    const val DOG_ID_KEY = "dog_id"
    const val DOG_NAME_KEY = "dog_name"
    const val DOG_AGE_KEY = "dog_age"
    const val DOG_KIND_KEY = "dog_kind"
    const val DOG_MEALS_KEY = "dog_meals" // 리스트를 , 구분자를 통해서 스트링으로 저장
}