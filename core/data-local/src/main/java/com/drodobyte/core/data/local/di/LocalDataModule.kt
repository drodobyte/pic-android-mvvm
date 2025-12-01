package com.drodobyte.core.data.local.di

import android.content.Context
import com.drodobyte.core.data.local.Factory
import com.drodobyte.core.data.local.FoodDao
import com.drodobyte.core.data.local.Database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class LocalDataModule {

    @Provides
    @Singleton
    fun localDataSource(dao: FoodDao) =
        Factory.sourceData(dao)

    @Provides
    @Singleton
    fun foodDao(database: Database) =
        Factory.dao(database)

    @Provides
    @Singleton
    fun database(@ApplicationContext context: Context) =
        Factory.database(context)
}
