package com.drodobyte.core.data.repository

import android.content.Context
import com.drodobyte.core.data.room.FoodDao
import com.drodobyte.data.retrofit.NutrientsApi

object Factory {

    fun foodRepository(dao: FoodDao, api: NutrientsApi, c: Context): FoodRepository =
        DefaultFoodRepository(dao, api, c)
}
