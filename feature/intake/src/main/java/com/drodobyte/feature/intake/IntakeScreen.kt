package com.drodobyte.feature.intake

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.drodobyte.core.data.model.Food
import com.drodobyte.feature.intake.IntakeViewModel.State
import com.drodobyte.feature.intake.util.IntEditField
import com.drodobyte.feature.intake.util.SearchBar
import kotlinx.coroutines.launch

@Composable
@PreviewScreenSizes
fun IntakeScreen(
    viewModel: IntakeViewModel = hiltViewModel()
) {
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        },
    ) { padding ->
        val state by viewModel.uiState.collectAsStateWithLifecycle()
        state.Intake(
            modifier = Modifier.padding(padding),
            onWeight = { viewModel.weight(it) },
            onSearch = { viewModel.search(it) },
            onSelected = { viewModel.selectedFood(it) },
        )

        val scope = rememberCoroutineScope()
        val error = stringResource(R.string.error_message)
        LaunchedEffect(Unit) {
            if (state.isError) {
                scope.launch { snackbarHostState.showSnackbar(error) }
            }
        }
    }
}

@Composable
private fun State.Intake(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit,
    onSelected: (Food?) -> Unit,
    onWeight: (Int?) -> Unit,
) =
    Column(modifier.padding(24.dp)) {
        RecommendedIntake(Modifier.fillMaxWidth())
        Spacer(Modifier.height(24.dp))
        Row(verticalAlignment = Alignment.Top) {
            IntEditField(
                number = userWeight,
                label = R.string.weight,
                placeholder = R.string.kg,
                onChange = onWeight,
                modifier = Modifier.weight(1f)
            )
            Spacer(Modifier.width(12.dp))
            FoodSearch(
                query = search,
                foods = foods,
                onSearch = onSearch,
                onSelected = onSelected,
                modifier = Modifier
                    .offset(y = (-36).dp)
                    .weight(3f)
            )
        }
    }

@Composable
private fun FoodSearch(
    query: String,
    foods: List<Food>,
    onSearch: (String) -> Unit,
    onSelected: (Food?) -> Unit,
    modifier: Modifier = Modifier
) =
    SearchBar(
        modifier = modifier,
        query = query,
        placeholder = R.string.search_food,
        results = foods.map { it.brandName },
        onSearch = onSearch,
        onSelected = { name -> onSelected(foods.find { it.brandName == name }) }
    )

@Composable
private fun State.RecommendedIntake(modifier: Modifier = Modifier) =
    Card(modifier, elevation = CardDefaults.elevatedCardElevation(defaultElevation = 3.dp)) {
        Text(
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(10.dp),
            text = when {
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
    }

private val Food.brandName get() = "$name${brand?.let { "($it)" } ?: ""}"
