package com.drodobyte.feature.nutriens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.drodobyte.feature.nutriens.NutrientsViewModel.State

@Composable
fun NutrientsScreen(
    modifier: Modifier = Modifier,
    viewModel: NutrientsViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    NutrientsScreen(state, modifier)
}

@Composable
private fun NutrientsScreen(state: State, modifier: Modifier) {
    Text(state.toString(), modifier)
}

