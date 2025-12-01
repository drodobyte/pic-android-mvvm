package com.drodobyte.core.data.local

import kotlinx.coroutines.flow.Flow

class FoodLocalDataSource internal constructor(
    private val dao: FoodDao,
) {
    fun byName(name: String): Flow<List<Food>> = dao.byName(name)

    suspend fun save(foods: List<Food>) = dao.upsert(foods)
}
