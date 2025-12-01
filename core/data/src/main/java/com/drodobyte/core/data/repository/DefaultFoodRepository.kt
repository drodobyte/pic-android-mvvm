package com.drodobyte.core.data.repository

import com.drodobyte.core.data.local.FoodLocalSourceData
import com.drodobyte.core.data.model.Food
import com.drodobyte.data.remote.FoodRemoteDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

internal class DefaultFoodRepository @Inject constructor(
    val local: FoodLocalSourceData,
    val remote: FoodRemoteDataSource,
) : FoodRepository {

    override fun byName(name: String) =
        fetchAndSave(name.lowercase())

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun fetchAndSave(name: String): Flow<List<Food>> =
        local.byName(name)
            .flatMapLatest {
                if (it.isEmpty()) {
                    remote.byName(name)
                        .onEach { local.save(it.modelsFromRemote.modelsToLocal) }
                        .flatMapLatest { local.byName(name) }
                } else {
                    flowOf(it)
                }
            }.map { it.toModel }
}
