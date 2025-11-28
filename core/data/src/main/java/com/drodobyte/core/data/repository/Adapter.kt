package com.drodobyte.core.data.repository

import com.drodobyte.core.data.model.Amount
import com.drodobyte.core.data.model.Food
import com.drodobyte.core.data.model.Id
import com.drodobyte.core.data.model.Nutrition

internal val List<com.drodobyte.core.data.room.Food>.models: List<Food>
    get() = map { it.model }

internal val com.drodobyte.core.data.room.Food.model: Food
    get() = Food(
        Id(id),
        name,
        Nutrition(
            Amount(
                amount,
                Amount.What.entries[amountWhat.toInt()]
            ),
            kcal,
            protein
        )
    )
