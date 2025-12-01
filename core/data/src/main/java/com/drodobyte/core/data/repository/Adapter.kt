package com.drodobyte.core.data.repository

import com.drodobyte.core.data.model.Food
import com.drodobyte.core.data.remote.Response
import com.drodobyte.core.data.remote.Response.Food.What
import com.drodobyte.core.data.remote.Response.Food.What.Energy
import com.drodobyte.core.data.remote.Response.Food.What.Protein

internal val List<com.drodobyte.core.data.local.Food>.toModel: List<Food>
    get() = map { it.modelFromLocal }

internal val List<Food>.modelsToLocal get() = map { it.toLocal }

internal val Response.modelsFromRemote get() = foods.modelsFromRemote

private val Food.toLocal
    get() = com.drodobyte.core.data.local.Food(id, name, brand, energy, protein)
private val com.drodobyte.core.data.local.Food.modelFromLocal
    get() = Food(id, name, brand, kcal, protein)
private val List<Response.Food>.modelsFromRemote get() = map { it.modelFromRemote }
private val Response.Food.modelFromRemote
    get() = Food(fdcId, description, brandName, energy, protein)
private val Response.Food.protein get() = nutrientValue(Protein)
private val Response.Food.energy get() = nutrientValue(Energy).toInt()
private fun Response.Food.nutrientValue(what: What) =
    foodNutrients.find { it.nutrientId == what.id }?.value ?: 0f
