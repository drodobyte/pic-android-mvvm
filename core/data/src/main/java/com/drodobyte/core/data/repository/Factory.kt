package com.drodobyte.core.data.repository

import com.drodobyte.core.data.room.FoodDao

object Factory {

    fun foodRepository(dao: FoodDao): FoodRepository = DefaultFoodRepository(dao)
}
