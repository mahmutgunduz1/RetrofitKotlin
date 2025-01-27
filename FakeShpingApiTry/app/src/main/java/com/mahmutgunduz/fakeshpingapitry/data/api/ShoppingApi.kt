package com.mahmutgunduz.fakeshpingapitry.data.api

import com.mahmutgunduz.fakeshpingapitry.data.model.Product
import retrofit2.Response
import retrofit2.http.GET

interface ShoppingApi {
    @GET("products")
    suspend fun getProducts(): Response<List<Product>>
} 