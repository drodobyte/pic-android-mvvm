package com.drodobyte.feature.nutriens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
    onError: () -> Unit
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    IntakeScreen(state, modifier, onError)
}

@Composable
private fun IntakeScreen(
    state: State,
    modifier: Modifier,
    onError: () -> Unit
) =
    Box(modifier) {
        when (state) {
            State.Loading -> Loading()
            is State.Success -> Foods(state.foods)
            is State.Error -> onError()
        }
    }

@Composable
private fun Loading() =
    Text("Loading")

@Composable
private fun Foods(foods: List<Food>) =
    LazyColumn {
        items(foods, key = { it.id }) {
            Text("${it.name} (${it.brand})")
        }
    }
