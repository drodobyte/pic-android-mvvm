package com.drodobyte.domain.usecase

import com.drodobyte.domain.util.times

class RecommendedIntakeUseCase() {

    data class Intake(
        /** Amount of protein per person per day requirements (Gr) */
        val proteinIntake: IntRange,
        /** Amount of food to meet protein requirements (Gr) */
        val foodIntake: IntRange
    )

    /**
     * [userWeight] User weight (Kg)
     *
     * [foodProteinContent] Protein amount in food (Gr)
     * @return Food intake data
     */
    operator fun invoke(
        userWeight: Int,
        foodProteinContent: Int
    ) =
        Intake(
            proteinIntake = (1.5f..2.5f) * userWeight,
            foodIntake = (1.5f..2.5f) * (userWeight * 100f / foodProteinContent).toInt()
        )
}
