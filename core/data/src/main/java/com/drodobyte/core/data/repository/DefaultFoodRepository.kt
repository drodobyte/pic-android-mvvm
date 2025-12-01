package com.drodobyte.core.data.repository

import android.content.Context
import com.drodobyte.core.data.local.FoodLocalSourceData
import com.drodobyte.core.data.model.Food
import com.drodobyte.data.retrofit.NutrientsApi
import com.drodobyte.data.retrofit.NutrientsApiMock
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject

// todo make a use case
internal class DefaultFoodRepository @Inject constructor(
    val local: FoodLocalSourceData,
    api: NutrientsApi, // fixme
    c: Context
) : FoodRepository {

    val mock by lazy { NutrientsApiMock(c) }

    override fun foodsByName(name: String) =
        name.lowercase().let { lower ->
            combine(
                local.byName(name).map { it.toModel },
                mock.foods().map { it.modelsFromRemote }
            ) { local, remote ->
                local.ifEmpty {
                    remote.filter { lower in it.name.lowercase() }
                }
            }
        }

    override suspend fun save(foods: List<Food>) =
        local.save(foods.modelsToLocal)
}
