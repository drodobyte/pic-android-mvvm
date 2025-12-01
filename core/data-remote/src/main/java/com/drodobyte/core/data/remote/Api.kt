package com.drodobyte.core.data.remote

import android.content.Context
import com.drodobyte.data.remote.R
import com.google.gson.Gson
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import java.io.InputStreamReader

internal interface Api {

    suspend fun foods(query: String, page: Int = 0, size: Int = 10): Response
}

data class Response(
    val foods: List<Food>
) {
    data class Food(
        val fdcId: Long,
        val description: String,
        val brandName: String,
        val foodNutrients: List<Nutrient>
    ) {
        data class Nutrient(
            val nutrientId: Int,
            val value: Float
        )

        enum class What(val id: Int) {
            Protein(1003), Energy(1008)
        }
    }
}

internal class MockApi(private val context: Context) : Api {

    override suspend fun foods(
        query: String,
        page: Int,
        size: Int
    ): Response =
        with(context.resources) {
            Gson().fromJson(
                InputStreamReader(openRawResource(R.raw.food_api_mock_response)),
                Response::class.java
            )
        }
}

internal interface RealApi : Api {

    @Headers("X-Api-Key: DEMO_KEY")
    @GET("foods/search")
    override suspend fun foods(
        @Query("query") query: String,
        @Query("pageNumber") page: Int,
        @Query("pageSize") size: Int,
    ): Response
}
