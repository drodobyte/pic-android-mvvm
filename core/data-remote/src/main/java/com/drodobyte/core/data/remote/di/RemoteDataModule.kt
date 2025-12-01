package com.drodobyte.core.data.remote.di

import android.content.Context
import com.drodobyte.core.data.remote.Api
import com.drodobyte.core.data.remote.Factory
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
