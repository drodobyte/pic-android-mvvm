package com.drodobyte.core.data.repository

import android.content.Context
import com.drodobyte.core.data.room.FoodDao
import com.drodobyte.data.retrofit.NutrientsApi
import com.drodobyte.data.retrofit.NutrientsApiMock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject

// todo make a use case
internal class DefaultFoodRepository
@Inject constructor(
    dao: FoodDao,
    api: NutrientsApi, // fixme
    c: Context
) : FoodRepository {

    val mock by lazy { NutrientsApiMock(c) }

    @OptIn(ExperimentalCoroutinesApi::class)
    override val foods =
        combine(
            dao.all().map { it.modelsFromLocal },
            mock.foods().map { it.modelsFromRemote }
        ) { local, remote ->
            local.ifEmpty { remote }
        }
}
