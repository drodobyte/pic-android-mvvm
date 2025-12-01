package com.drodobyte.core.data.repository

import android.content.Context
import com.drodobyte.core.data.local.FoodLocalSourceData
import com.drodobyte.data.retrofit.NutrientsApi

internal object Factory {

    fun foodRepository(data: FoodLocalSourceData, api: NutrientsApi, c: Context): FoodRepository =
        DefaultFoodRepository(data, api, c)
}
