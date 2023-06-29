package com.example.receitasmexicanas.modelResponse

import java.lang.reflect.Method

data class FoodDetailsResponse(

    val description: String,
    val difficulty: String,
    val id: String,
    val image: String,
    val ingredients: List<String>,
    val method: List<Method>,
    val portion: String,
    val time: String,
    val title: String,

    )
