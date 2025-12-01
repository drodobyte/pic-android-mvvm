package com.drodobyte.data.remote

import kotlinx.coroutines.flow.flow

class FoodRemoteDataSource internal constructor(
    private val api: Api
) {

    fun byName(name: String) =
        flow { emit(api.foods(name)) }
}
