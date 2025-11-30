package com.drodobyte.data.retrofit

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

// todo Encapsulate in RemoteDataSource
interface NutrientsApi {

    @Headers("X-Api-Key: DEMO_KEY")
    @GET("foods/search")
    suspend fun foods(
        @Query("query") query: String,
        @Query("pageNumber") page: Int = 0,
        @Query("pageSize") size: Int = 10,
    ): FoodResponse
}
