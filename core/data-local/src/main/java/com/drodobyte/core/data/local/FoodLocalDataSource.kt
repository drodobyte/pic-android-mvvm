package com.drodobyte.core.data.local

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FoodLocalDataSource internal constructor(
    private val dao: FoodDao,
) {
    fun byName(name: String): Flow<List<Food>> = flow { emit(dao.byName(name)) }

    suspend fun save(foods: List<Food>) = dao.upsert(foods)
}
