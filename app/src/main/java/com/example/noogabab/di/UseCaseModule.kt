package com.example.noogabab.di

import com.example.noogabab.domain.repository.DogRepository
import com.example.noogabab.domain.repository.GroupRepository
import com.example.noogabab.domain.repository.TimelineRepository
import com.example.noogabab.domain.repository.UserRepository
import com.example.noogabab.domain.usecase.DogUseCase
import com.example.noogabab.domain.usecase.GroupUseCase
import com.example.noogabab.domain.usecase.TimelineUseCase
import com.example.noogabab.domain.usecase.UserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@InstallIn(ActivityRetainedComponent::class)
@Module
object UseCaseModule {
    @Provides
    fun provideTimelineUseCase(timelineRepository: TimelineRepository) =
        TimelineUseCase(timelineRepository)

    @Provides
    fun provideGroupUseCase(groupRepository: GroupRepository) = GroupUseCase(groupRepository)

    @Provides
    fun provideDogUseCase(dogRepository: DogRepository) = DogUseCase(dogRepository)

    @Provides
    fun provideUserUseCase(userRepository: UserRepository) = UserUseCase(userRepository)
}