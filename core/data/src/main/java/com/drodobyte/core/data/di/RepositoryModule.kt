package com.drodobyte.core.data.di

import com.drodobyte.core.data.local.FoodLocalSourceData
import com.drodobyte.core.data.repository.DefaultFoodRepository
import com.drodobyte.core.data.repository.FoodRepository
import com.drodobyte.data.remote.FoodRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun foodRepository(
        local: FoodLocalSourceData,
        remote: FoodRemoteDataSource
    ): FoodRepository =
        DefaultFoodRepository(local, remote)
}
