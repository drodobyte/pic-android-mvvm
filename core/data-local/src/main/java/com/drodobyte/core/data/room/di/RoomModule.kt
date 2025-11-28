package com.drodobyte.core.data.room.di

import android.content.Context
import com.drodobyte.core.data.room.Factory
import com.drodobyte.core.data.room.PicRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Provides
    fun foodDao(database: PicRoomDatabase) =
        database.foodDao()

    @Provides
    @Singleton
    fun roomDatabase(@ApplicationContext context: Context) =
        Factory.database(context)
}
