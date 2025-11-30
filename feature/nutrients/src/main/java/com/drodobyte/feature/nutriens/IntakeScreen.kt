package com.drodobyte.feature.nutriens

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.drodobyte.core.data.model.Food
import com.drodobyte.feature.nutriens.IntakeViewModel.State

@Composable
fun IntakeScreen(
    modifier: Modifier = Modifier,
    viewModel: IntakeViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    IntakeScreen(state, modifier)
}

@Composable
private fun IntakeScreen(state: State, modifier: Modifier) =
    Box(modifier) {
        when (state) {
            State.Loading -> Loading()
            is State.Success -> Foods(state.foods)
            is State.Error -> Error()
        }
    }

@Composable
private fun Loading() =
    Text("Loading")

@Composable
private fun Foods(foods: List<Food>) =
    Text(foods.joinToString { "${it.name} (${it.brand})" })

@Composable
private fun Error() =
    Text("Unexpected error")
