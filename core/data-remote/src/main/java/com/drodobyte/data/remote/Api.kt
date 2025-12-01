package com.drodobyte.data.remote

internal interface Api {

    suspend fun foods(query: String, page: Int = 0, size: Int = 10): Response
}
