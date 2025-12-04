package com.drodobyte.feature.intake

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewDynamicColors
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import com.drodobyte.core.data.model.Food
import com.drodobyte.feature.intake.IntakeViewModel.State
import kotlin.annotation.AnnotationRetention.BINARY
import kotlin.annotation.AnnotationTarget.ANNOTATION_CLASS
import kotlin.annotation.AnnotationTarget.FUNCTION

@Composable
@PreviewAll
private fun NoUserInput() {
    IntakeScreen(State(), {}, {}, {})
}

@Composable
@PreviewAll
private fun WithUserInput() {
    val food = Food(
        id = 0,
        name = "Meat",
        brand = "Brand",
        energy = 100,
        protein = 20f,
    )
    IntakeScreen(
        State(
            errors = null,
            userWeight = 70,
            search = "meat",
            selectedFood = food,
            foods = listOf(food, food),
            proteinIntake = (150..250),
            foodIntake = (15..25),
        ), {}, {}, {})
}

@Retention(BINARY)
@Target(ANNOTATION_CLASS, FUNCTION)
@PreviewFontScale
@PreviewLightDark
@PreviewScreenSizes
@PreviewDynamicColors
private annotation class PreviewAll
