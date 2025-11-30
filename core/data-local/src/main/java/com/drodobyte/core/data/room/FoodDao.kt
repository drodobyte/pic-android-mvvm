package com.drodobyte.core.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

// todo encapsulate in LocalDataSource
@Dao
interface FoodDao {

    @Query("SELECT * FROM food")
    fun all(): Flow<List<Food>>

    @Insert
    suspend fun insert(food: Food)
}
