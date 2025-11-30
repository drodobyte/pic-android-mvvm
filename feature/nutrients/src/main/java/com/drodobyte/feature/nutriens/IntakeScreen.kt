package com.drodobyte.feature.nutriens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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
    IntakeScreen(
        state = state,
        modifier = modifier,
        onQuery = { viewModel.search(it) },
        onError = onError
    )
}

@Composable
private fun IntakeScreen(
    state: State,
    modifier: Modifier,
    onQuery: (String) -> Unit,
    onError: () -> Unit,
) = with(state) {
    Box(modifier) {
        when {
            isLoading -> Loading()
            isError -> onError()
            else -> QueryFoods(query, foods, onQuery)
        }
    }
}

@Composable
private fun Loading() =
    Text("Loading")

@Composable
private fun QueryFoods(query: String, foods: List<Food>, onChange: (String) -> Unit) =
    Column {
        OutlinedTextField(
            value = query,
            onValueChange = {
                onChange(it)
                            },
            label = { Text(stringResource(R.string.search_food)) },
            placeholder = { Text(stringResource(R.string.name)) },
        )

        LazyColumn {
            items(foods, key = { it.id }) {
                Text("${it.name} (${it.brand})")
            }
        }
    }
