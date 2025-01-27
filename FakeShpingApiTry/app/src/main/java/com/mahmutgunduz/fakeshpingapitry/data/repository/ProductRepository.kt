package com.mahmutgunduz.fakeshpingapitry.data.repository

import com.mahmutgunduz.fakeshpingapitry.data.api.RetrofitInstance
import com.mahmutgunduz.fakeshpingapitry.data.model.Product

class ProductRepository {
    private val api = RetrofitInstance.api
    
    suspend fun getProducts(): List<Product> {
        val response = api.getProducts()
        if (response.isSuccessful) {
            return response.body() ?: emptyList()
        }
        throw Exception(response.message())
    }
} 