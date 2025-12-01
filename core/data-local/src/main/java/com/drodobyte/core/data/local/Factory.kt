package com.drodobyte.core.data.local

import android.content.Context
import androidx.room.Room

internal object Factory {

    fun sourceData(dao: FoodDao) =
        FoodLocalSourceData(dao)

    fun database(context: Context) =
        Room.databaseBuilder(
            context,
            Database::class.java,
            "pic"
        ).build()

    fun dao(database: Database) =
        database.foodDao()
}
