package com.drodobyte.core.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Food(
    @PrimaryKey
    val id: Long = 0,
    val name: String,
    val brand: String,
    val kcal: Int,
    val protein: Float,
)
