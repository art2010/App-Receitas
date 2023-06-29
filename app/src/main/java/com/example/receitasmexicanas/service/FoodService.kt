package com.example.receitasmexicanas.service

import com.example.receitasmexicanas.modelResponse.FoodDetailsResponse
import com.example.receitasmexicanas.modelResponse.FoodResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface FoodService {

    @GET("/")
    fun getFood(): Call<List<FoodResponse>>

    @GET("/{id}")
    fun getFoodById(@Path("id") id: Int): Call<FoodDetailsResponse>

}