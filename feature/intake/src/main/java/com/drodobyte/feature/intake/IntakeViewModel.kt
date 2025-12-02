package com.drodobyte.feature.intake

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.drodobyte.core.data.model.Food
import com.drodobyte.core.data.repository.FoodRepository
import com.drodobyte.domain.usecase.RecommendedIntakeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IntakeViewModel @Inject constructor(
    private val foodRepository: FoodRepository
) : ViewModel() {

    private val useCase = RecommendedIntakeUseCase()
    private val search = MutableStateFlow("")
    private val weight = MutableStateFlow<Int?>(null)
    private val selectedFood = MutableStateFlow<Food?>(null)

    val uiState = combine(
        weight,
        selectedFood,
        search,
        searchFoods()
    ) { weight, selectedFood, search, foods ->
        combineState(weight, selectedFood, search, foods)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = State()
    )

    fun search(query: String) = search.set { query }
    fun weight(kg: Int?) = weight.set { kg }
    fun selectedFood(food: Food?) = selectedFood.set { food }

    data class State(
        val userWeight: Int? = null,
        val selectedFood: Food? = null,
        val search: String = "",
        val foods: List<Food> = emptyList(),
        val proteinIntake: IntRange? = null,
        val foodIntake: IntRange? = null,
        val isError: Boolean = false,
        val isLoading: Boolean = false,
    ) {
        val userInputOk = userWeight != null && selectedFood != null
    }

    private fun combineState(
        weight: Int?,
        selectedFood: Food?,
        search: String,
        foods: List<Food>
    ) =
        State(weight, selectedFood, search, foods).run {
            takeIf { userInputOk }?.let {
                useCase(
                    userWeight = userWeight!!,
                    foodProteinContent = selectedFood!!.protein.toInt()
                ).let {
                    copy(
                        proteinIntake = it.proteinIntake,
                        foodIntake = it.foodIntake
                    )
                }
            } ?: this
        }

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    private fun searchFoods() = search
        .filter { it.isNotBlank() }
        .distinctUntilChanged()
        .debounce(500L)
        .flowOn(Dispatchers.Main)
        .flatMapLatest {
            foodRepository
                .byName(it)
                .catch { emptyList<Food>() }
                .flowOn(Dispatchers.IO)
        }

    private fun <T> MutableStateFlow<T>.set(data: () -> T) =
        viewModelScope.launch { update { data() } }
}
