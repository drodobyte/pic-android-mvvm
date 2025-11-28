package com.drodobyte.core.data.repository

import com.drodobyte.core.data.room.FoodDao
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class DefaultFoodRepository
@Inject constructor(
    dao: FoodDao
) : FoodRepository {

    override val foods =
        dao.all().map { it.models }
}
