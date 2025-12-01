package com.drodobyte.core.data.di

import com.drodobyte.core.data.local.FoodLocalDataSource
import com.drodobyte.core.data.repository.DefaultFoodRepository
import com.drodobyte.core.data.repository.FoodRepository
import com.drodobyte.core.data.remote.FoodRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class RepositoryModule {

    @Singleton
    @Provides
    fun foodRepository(
        local: FoodLocalDataSource,
        remote: FoodRemoteDataSource
    ): FoodRepository =
        DefaultFoodRepository(local, remote)
}
