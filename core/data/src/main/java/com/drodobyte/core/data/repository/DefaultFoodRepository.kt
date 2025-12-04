package com.drodobyte.core.data.repository

import com.drodobyte.core.data.local.FoodLocalDataSource
import com.drodobyte.core.data.model.Food
import com.drodobyte.core.data.remote.FoodRemoteDataSource
import com.drodobyte.core.data.repository.Adapter.Local.Companion.toLocal
import com.drodobyte.core.data.repository.Adapter.Local.Companion.toModel
import com.drodobyte.core.data.repository.Adapter.Remote.Companion.toModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

internal class DefaultFoodRepository(
    val local: FoodLocalDataSource,
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
                        .onEach { local.save(it.toModel.toLocal) }
                        .flatMapLatest { local.byName(name) }
                } else {
                    flowOf(it)
                }
            }.map { it.toModel }
}
