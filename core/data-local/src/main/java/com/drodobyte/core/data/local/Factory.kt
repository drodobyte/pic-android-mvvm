package com.drodobyte.core.data.local

import android.content.Context
import androidx.room.Room

internal object Factory {

    fun dataSource(dao: FoodDao) =
        FoodLocalDataSource(dao)

    fun database(context: Context) =
        Room.databaseBuilder(
            context,
            Database::class.java,
            "pic"
        ).build()

    fun dao(database: Database) =
        database.foodDao()
}
