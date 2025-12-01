package com.drodobyte.core.data.local

import android.content.Context
import androidx.room.Room

internal object Factory {

    fun database(context: Context) =
        Room.databaseBuilder(
            context,
            PicRoomDatabase::class.java,
            "pic"
        ).build()
}
