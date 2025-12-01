package com.drodobyte.feature.nutriens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.drodobyte.core.data.model.Food
import com.drodobyte.core.data.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IntakeViewModel @Inject constructor(
    foodRepository: FoodRepository
) : ViewModel() {

    private val search = MutableStateFlow(State())

    val uiState = search
        .filter { it.query.isNotBlank() }
        .distinctUntilChanged()
//        .debounce(10L) fixme
        .flatMapLatest { state ->
            foodRepository
                .byName(state.query)
                .map { state.copy(foods = it, isLoading = false) }
                .catch { state.copy(isError = true, isLoading = false) }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = State()
        )

    fun search(query: String) {
        viewModelScope.launch {
            search.update { it.copy(query = query) }
        }
    }

    data class State(
        val query: String = "",
        val foods: List<Food> = emptyList(),
        val isError: Boolean = false,
        val isLoading: Boolean = false,
    )
}
