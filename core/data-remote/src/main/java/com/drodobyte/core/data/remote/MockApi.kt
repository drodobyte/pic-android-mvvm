package com.drodobyte.core.data.remote

import android.content.Context
import com.drodobyte.data.remote.R
import com.google.gson.Gson
import java.io.InputStreamReader

// todo move to mock variant
internal class MockApi(private val context: Context) : Api {

    override suspend fun foods(query: String, page: Int, size: Int) =
        Gson().fromJson(
            InputStreamReader(context.resources.openRawResource(R.raw.food_api_mock_response)),
            Response::class.java
        )
}
