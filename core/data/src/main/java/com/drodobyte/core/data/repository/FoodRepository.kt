package com.drodobyte.core.data.repository

import com.drodobyte.core.data.model.Food
import kotlinx.coroutines.flow.Flow

interface FoodRepository {
    val foods: Flow<List<Food>>
}
