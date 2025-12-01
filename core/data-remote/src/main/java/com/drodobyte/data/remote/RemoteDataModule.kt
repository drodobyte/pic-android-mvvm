package com.drodobyte.data.remote

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class RemoteDataModule {

    @Provides
    @Singleton
    fun dataSource(api: Api) =
        Factory.dataSource(api)

    @Provides
    @Singleton
    fun api(@ApplicationContext context: Context) =
        Factory.api(context, mock = true)
}
