package com.drodobyte.data.remote

import android.content.Context
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory.create

internal object Factory {

    fun dataSource(api: Api) =
        FoodRemoteDataSource(api)

    fun api(context: Context, mock: Boolean): Api =
        if (mock) {
            MockApi(context)
        } else {
            Retrofit.Builder()
                .baseUrl("https://api.nal.usda.gov/fdc/v1/")
                .addConverterFactory(create())
                .build()
                .create(RealApi::class.java)
        }
}
