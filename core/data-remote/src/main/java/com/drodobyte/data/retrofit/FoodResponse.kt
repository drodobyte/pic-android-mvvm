package com.drodobyte.data.retrofit

data class FoodResponse(
    val foods: List<Food>
) {
    data class Food(
        val fdcId: Long,
        val description: String,
        val brandName: String,
        val foodNutrients: List<Nutrient>
    ) {
        data class Nutrient(
            val nutrientId: Int,
            val value: Float
        )

        enum class What(val id: Int) {
            Protein(1003), Energy(1008)
        }
    }
}
