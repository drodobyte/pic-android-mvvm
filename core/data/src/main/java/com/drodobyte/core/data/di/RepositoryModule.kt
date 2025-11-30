package com.drodobyte.core.data.di

import android.content.Context
import com.drodobyte.core.data.repository.Factory
import com.drodobyte.core.data.room.FoodDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun foodRepository(
        dao: FoodDao,
        @ApplicationContext c: Context
    ) =
        Factory.foodRepository(
            dao,
            com.drodobyte.data.retrofit.Factory.api,
            c
        )
}
