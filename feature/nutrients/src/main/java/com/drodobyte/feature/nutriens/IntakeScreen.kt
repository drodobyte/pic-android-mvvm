package com.drodobyte.feature.nutriens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
private fun IntakeScreen(state: State, modifier: Modifier) {
    Text(state.toString(), modifier)
}

