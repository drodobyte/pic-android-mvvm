package com.drodobyte.core.data.repository

import com.drodobyte.core.data.model.Food
import com.drodobyte.data.retrofit.FoodResponse
import com.drodobyte.data.retrofit.FoodResponse.Food.What
import com.drodobyte.data.retrofit.FoodResponse.Food.What.Energy
import com.drodobyte.data.retrofit.FoodResponse.Food.What.Protein

internal val List<com.drodobyte.core.data.room.Food>.modelsFromLocal: List<Food>
    get() = map { it.modelFromLocal }

internal val List<Food>.modelsToLocal get() = map { it.modelToLocal }

internal val FoodResponse.modelsFromRemote get() = foods.modelsFromRemote

private val Food.modelToLocal
    get() = com.drodobyte.core.data.room.Food(id, name, brand, energy, protein)
private val com.drodobyte.core.data.room.Food.modelFromLocal
    get() = Food(id, name, brand, kcal, protein)
private val List<FoodResponse.Food>.modelsFromRemote get() = map { it.modelFromRemote }
private val FoodResponse.Food.modelFromRemote
    get() = Food(fdcId, description, brandName, energy, protein)
private val FoodResponse.Food.protein get() = nutrientValue(Protein)
private val FoodResponse.Food.energy get() = nutrientValue(Energy).toInt()
private fun FoodResponse.Food.nutrientValue(what: What) =
    foodNutrients.find { it.nutrientId == what.id }?.value ?: 0f
