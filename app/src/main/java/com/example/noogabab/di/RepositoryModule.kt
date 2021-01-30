package com.example.noogabab.di

import com.example.noogabab.data.api.TimelineService
import com.example.noogabab.domain.repository.TimelineRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@InstallIn(ActivityRetainedComponent::class)
@Module
object RepositoryModule {
    @Provides
    fun provideTimelineRepo(timelineService: TimelineService) = TimelineRepository(timelineService)
}