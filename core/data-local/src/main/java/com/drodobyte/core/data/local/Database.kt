package com.drodobyte.core.data.local

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.Update

@Entity
class Food(
    @PrimaryKey
    val id: Long = 0,
    val name: String,
    val brand: String?,
    val kcal: Int,
    val protein: Float,
)

@Database(entities = [Food::class], version = 1)
internal abstract class Database : RoomDatabase() {
    abstract fun foodDao(): FoodDao
}

@Dao
internal interface FoodDao {

    @Query("SELECT * FROM food WHERE name LIKE '%' || :name || '%'")
    suspend fun byName(name: String): List<Food>

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
