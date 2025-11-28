package com.drodobyte.core.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Food(
    @PrimaryKey
    val id: Int = 0,
    val name: String,
    val kcal: Int,
    val protein: Float,
    val amount: Float,
    val amountWhat: Short
)
