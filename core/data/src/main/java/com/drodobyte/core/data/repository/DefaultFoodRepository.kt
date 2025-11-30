package com.drodobyte.core.data.repository

import android.content.Context
import com.drodobyte.core.data.model.Food
import com.drodobyte.core.data.room.FoodDao
import com.drodobyte.data.retrofit.NutrientsApi
import com.drodobyte.data.retrofit.NutrientsApiMock
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject

// todo make a use case
internal class DefaultFoodRepository
@Inject constructor(
    private val dao: FoodDao,
    api: NutrientsApi, // fixme
    c: Context
) : FoodRepository {

    val mock by lazy { NutrientsApiMock(c) }

    override fun foodsByName(name: String) =
        name.lowercase().let { lower ->
            combine(
                dao.byName(name).map { it.modelsFromLocal },
                mock.foods().map { it.modelsFromRemote }
            ) { local, remote ->
                local.ifEmpty {
                    remote.filter { lower in it.name.lowercase() }
                }
            }
        }

    override suspend fun save(foods: List<Food>) =
        dao.insertOrUpdate(foods.modelsToLocal)
}
