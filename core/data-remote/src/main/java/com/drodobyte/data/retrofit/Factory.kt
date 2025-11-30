package com.drodobyte.data.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Factory {

    val api by lazy {
        retrofit()
            .create(NutrientsApi::class.java)
    }

    private fun retrofit() =
        Retrofit.Builder()
            .baseUrl("https://api.nal.usda.gov/fdc/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}
