package com.drodobyte.core.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

// todo encapsulate in LocalDataSource
@Dao
interface FoodDao {

    @Query("SELECT * FROM food WHERE name LIKE :name")
    fun byName(name: String): Flow<List<Food>>

    @Insert(onConflict = REPLACE)
    suspend fun insertOrUpdate(foods: List<Food>)
}
