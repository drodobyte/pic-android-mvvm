package com.drodobyte.feature.intake

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.drodobyte.core.data.model.Food
import com.drodobyte.feature.intake.IntakeViewModel.State
import com.drodobyte.feature.intake.util.IntEditField
import com.drodobyte.feature.intake.util.SearchBar

@Composable
fun IntakeScreen(
    modifier: Modifier = Modifier,
    viewModel: IntakeViewModel = hiltViewModel(),
    onError: () -> Unit
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    IntakeScreen(
        state = state,
        modifier = modifier,
        onWeight = { viewModel.weight(it) },
        onSearch = { viewModel.search(it) },
        onSelected = { viewModel.selectedFood(it) },
        onError = onError
    )
}

@Composable
private fun IntakeScreen(
    state: State,
    modifier: Modifier,
    onSearch: (String) -> Unit,
    onSelected: (Food?) -> Unit,
    onWeight: (Int?) -> Unit,
    onError: () -> Unit,
) = with(state) {
    Box(modifier) {
        when {
            isLoading -> Loading()
            isError -> onError()
            else -> IntakeForm(onWeight, onSearch, onSelected)
        }
    }
}

@Composable
private fun Loading() =
    Text("Loading")

@Composable
private fun State.IntakeForm(
    onWeight: (Int?) -> Unit,
    onSearch: (String) -> Unit,
    onSelected: (Food?) -> Unit,
) =
    Column {
        IntEditField(
            number = userWeight,
            label = R.string.weight,
            placeholder = R.string.kg,
            onChange = onWeight
        )
        FoodSearch(
            query = search,
            foods = foods,
            onSearch = onSearch,
            onSelected = onSelected
        )
        Spacer(Modifier.height(24.dp))
        RecommendedIntake()
    }

@Composable
private fun FoodSearch(
    query: String,
    foods: List<Food>,
    onSearch: (String) -> Unit,
    onSelected: (Food?) -> Unit
) =
    SearchBar(
        query = query,
        placeholder = R.string.search_food,
        results = foods.map { it.brandName },
        onSearch = onSearch,
        onSelected = { name -> onSelected(foods.find { it.brandName == name }) }
    )

@Composable
private fun State.RecommendedIntake() =
    Text(
        when {
            userInputOk -> stringResource(
                R.string.recommended_intake,
                userWeight!!,
                proteinIntake!!.first,
                proteinIntake.last,
                foodIntake!!.first,
                foodIntake.last
            )

            else -> stringResource(R.string.input_needed)
        }
    )

private val Food.brandName get() = "$name ($brand)"
