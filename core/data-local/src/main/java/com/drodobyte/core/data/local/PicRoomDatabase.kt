package com.drodobyte.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Food::class], version = 1)
internal abstract class PicRoomDatabase : RoomDatabase() {
    abstract fun foodDao(): FoodDao
}
