package com.mahmutgunduz.fakeshpingapitry.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://fakestoreapi.com/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: ShoppingApi by lazy {
        retrofit.create(ShoppingApi::class.java)
    }
} 