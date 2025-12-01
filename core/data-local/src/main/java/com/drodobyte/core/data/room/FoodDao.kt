package com.drodobyte.core.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

// todo encapsulate in LocalDataSource
@Dao
interface FoodDao {

    @Query("SELECT * FROM food WHERE name LIKE :name")
    fun byName(name: String): Flow<List<Food>>

    @Insert
    suspend fun insert(vararg foods: Food)

    @Update
    suspend fun update(vararg foods: Food)

    suspend fun upsert(foods: List<Food>) {
        foods.onEach { food ->
            runCatching {
                insert(food)
            }.onFailure {
                update(food)
            }
        }
    }
}
