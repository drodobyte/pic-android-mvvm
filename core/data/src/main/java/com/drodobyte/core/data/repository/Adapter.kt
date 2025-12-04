package com.drodobyte.core.data.repository

import com.drodobyte.core.data.model.Food
import com.drodobyte.core.data.remote.Response
import com.drodobyte.core.data.remote.Response.Food.What
import com.drodobyte.core.data.remote.Response.Food.What.Energy
import com.drodobyte.core.data.remote.Response.Food.What.Protein
import  com.drodobyte.core.data.local.Food as LocalFood
import  com.drodobyte.core.data.remote.Response.Food as RemFood

interface Adapter {
    interface Local {
        companion object {
            val List<LocalFood>.toModel get() = map { it.toModel }
            val List<Food>.toLocal get() = map { it.toLocal }

            private val Food.toLocal get() = LocalFood(id, name, brand, energy, protein)
            private val LocalFood.toModel get() = Food(id, name, brand, kcal, protein)
        }
    }

    interface Remote {
        companion object {
            val Response.toModel get() = foods.toModel

            private val List<RemFood>.toModel get() = map { it.toModel }
            private val RemFood.toModel get() = Food(fdcId, description, brandName, energy, protein)
            private val RemFood.protein get() = nutrientValue(Protein)
            private val RemFood.energy get() = nutrientValue(Energy).toInt()
            private fun RemFood.nutrientValue(what: What) =
                foodNutrients.find { it.nutrientId == what.id }?.value ?: 0f
        }
    }
}
