package com.example.receitasmexicanas.repository

import com.example.receitasmexicanas.modelResponse.FoodDetailsResponse
import com.example.receitasmexicanas.modelResponse.FoodResponse
import com.example.receitasmexicanas.retrofit.RetrofitConfiguration
import retrofit2.Call

class FoodRepository {

    private val foodApiService = RetrofitConfiguration.createFoodApiService()

    fun getFood(): Call<List<FoodResponse>> {
        return foodApiService.getFood()
    }

    fun getFoodById(id : Int): Call<FoodDetailsResponse>{
        return foodApiService.getFoodById(id)
    }

}