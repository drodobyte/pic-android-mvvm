package com.drodobyte.core.data.repository

import com.drodobyte.core.data.model.Food
import kotlinx.coroutines.flow.Flow

interface FoodRepository {
    fun foodsByName(name: String): Flow<List<Food>>
    suspend fun save(foods: List<Food>)
}
