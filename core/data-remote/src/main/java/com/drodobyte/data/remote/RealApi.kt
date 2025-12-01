package com.drodobyte.data.remote

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

internal interface RealApi : Api {

    @Headers("X-Api-Key: DEMO_KEY")
    @GET("foods/search")
    override suspend fun foods(
        @Query("query") query: String,
        @Query("pageNumber") page: Int,
        @Query("pageSize") size: Int,
    ): Response
}
