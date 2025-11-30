package com.drodobyte.data.retrofit

import android.content.Context
import com.google.gson.Gson
import kotlinx.coroutines.flow.flow
import java.io.InputStreamReader

// todo move to mock variant
class NutrientsApiMock(private val c: Context) {

    fun foods() =
        flow {
            emit(
                Gson().fromJson(
                    InputStreamReader(
                        c.resources.openRawResource(R.raw.food_api_mock_response)
                    ),
                    FoodResponse::class.java
                )
            )
        }
}
