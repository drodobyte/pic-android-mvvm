package com.drodobyte.domain.usecase

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
        (1.5f..2.5f).let {
            Intake(
                proteinIntake = it * userWeight,
                foodIntake = it * (userWeight * 100f / foodProteinContent).toInt()
            )
        }

    private operator fun ClosedFloatingPointRange<Float>.times(n: Int) =
        IntRange((start * n).toInt(), (endInclusive * n).toInt())
}
