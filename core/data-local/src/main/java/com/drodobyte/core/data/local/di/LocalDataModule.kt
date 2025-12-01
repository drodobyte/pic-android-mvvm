package com.drodobyte.core.data.local.di

import android.content.Context
import com.drodobyte.core.data.local.Factory
import com.drodobyte.core.data.local.FoodDao
import com.drodobyte.core.data.local.FoodLocalSourceData
import com.drodobyte.core.data.local.PicRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {

    @Provides
    internal fun localDataSource(dao: FoodDao) =
        FoodLocalSourceData(dao)

    @Provides
    internal fun foodDao(database: PicRoomDatabase) =
        database.foodDao()

    @Provides
    @Singleton
    internal fun roomDatabase(@ApplicationContext context: Context) =
        Factory.database(context)
}
