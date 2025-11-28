package com.drodobyte.feature.nutriens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.drodobyte.core.data.model.Food
import com.drodobyte.core.data.repository.FoodRepository
import com.drodobyte.feature.nutriens.IntakeViewModel.State.Error
import com.drodobyte.feature.nutriens.IntakeViewModel.State.Loading
import com.drodobyte.feature.nutriens.IntakeViewModel.State.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class IntakeViewModel @Inject constructor(
    foodRepository: FoodRepository
) : ViewModel() {

    val uiState = foodRepository
        .foods
        .map { Success(it) }
        .catch { Error(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = Loading
        )

    sealed interface State {
        object Loading : State
        data class Success(val foods: List<Food>) : State
        data class Error(val throwable: Throwable) : State
    }
}
