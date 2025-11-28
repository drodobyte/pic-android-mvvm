package com.drodobyte.core.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Food::class], version = 1)
abstract class PicRoomDatabase : RoomDatabase() {
    abstract fun foodDao(): FoodDao
}
