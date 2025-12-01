package com.drodobyte.core.data.repository

import com.drodobyte.core.data.local.FoodLocalSourceData
import com.drodobyte.core.data.model.Food
import com.drodobyte.data.remote.FoodRemoteDataSource
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject

// todo make a use case
internal class DefaultFoodRepository @Inject constructor(
    val local: FoodLocalSourceData,
    val remote: FoodRemoteDataSource,
) : FoodRepository {

    override fun byName(name: String) =
        name.lowercase().let { lower ->
            combine(
                local.byName(name).map { it.toModel },
                remote.byName(name).map { it.modelsFromRemote }
            ) { local, remote ->
                local.ifEmpty {
                    remote.filter { lower in it.name.lowercase() }
                }
            }
        }

    override suspend fun save(foods: List<Food>) =
        local.save(foods.modelsToLocal)
}
