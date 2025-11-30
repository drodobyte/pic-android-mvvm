package com.drodobyte.core.data.repository

import com.drodobyte.core.data.model.Food
import com.drodobyte.core.data.model.Id
import com.drodobyte.data.retrofit.FoodResponse
import com.drodobyte.data.retrofit.FoodResponse.Food.What
import com.drodobyte.data.retrofit.FoodResponse.Food.What.Energy
import com.drodobyte.data.retrofit.FoodResponse.Food.What.Protein

internal val List<com.drodobyte.core.data.room.Food>.modelsFromLocal: List<Food>
    get() = map { it.model }

internal val FoodResponse.modelsFromRemote get() = foods.models

private val com.drodobyte.core.data.room.Food.model
    get() = Food(Id(id), name, brand, kcal, protein)

private val List<FoodResponse.Food>.models get() = map { it.model }

private val FoodResponse.Food.model
    get() = Food(Id(fdcId), description, brandName, energy, protein)

private val FoodResponse.Food.protein get() = nutrientValue(Protein)
private val FoodResponse.Food.energy get() = nutrientValue(Energy).toInt()
private fun FoodResponse.Food.nutrientValue(what: What) =
    foodNutrients.find { it.nutrientId == what.id }?.value ?: 0f
