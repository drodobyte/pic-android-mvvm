package com.drodobyte.feature.intake

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.drodobyte.core.data.model.Food
import com.drodobyte.feature.intake.IntakeViewModel.State
import kotlinx.coroutines.delay

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
            else -> FoodSearch(query, foods, onQuery)
        }
    }
}

@Composable
private fun Loading() =
    Text("Loading")

@Composable
private fun FoodSearch(query: String, foods: List<Food>, onChange: (String) -> Unit) =
    Column {
        EditField(query, R.string.search_food, R.string.name, onChange)

        LazyColumn {
            items(foods, key = { it.id }) {
                Text("${it.name} (${it.brand})")
            }
        }
    }

@Composable
private fun EditField( // OutlinedTextField bug fix
    query: String,
    @StringRes label: Int,
    @StringRes placeholder: Int,
    onChange: (String) -> Unit
) {
    var txt by remember { mutableStateOf(query) }
    LaunchedEffect(txt) {
        delay(300)
        onChange(txt)
    }
    OutlinedTextField(
        value = txt,
        onValueChange = { txt = it },
        label = { Text(stringResource(label)) },
        placeholder = { Text(stringResource(placeholder)) }
    )
}
