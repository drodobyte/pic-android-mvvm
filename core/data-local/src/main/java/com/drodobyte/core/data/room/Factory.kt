package com.drodobyte.core.data.room

import android.content.Context
import androidx.room.Room

object Factory {

    fun database(context: Context) =
        Room.databaseBuilder(
            context,
            PicRoomDatabase::class.java,
            "pic"
        ).build()
}
