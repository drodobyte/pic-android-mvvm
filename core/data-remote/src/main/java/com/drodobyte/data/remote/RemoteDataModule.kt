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
class RemoteDataModule {

    @Provides
    internal fun dataSource(api: Api) =
        FoodRemoteDataSource(api)

    @Provides
    @Singleton
    internal fun api(@ApplicationContext context: Context) =
        Factory.api(context, mock = true)
}
