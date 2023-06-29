package com.example.receitasmexicanas.modelResponse

import com.google.gson.annotations.SerializedName

data class Method(
    @SerializedName("Step 1")
    val step_one: String?,
    @SerializedName("Step 2")
    val step_two: String?,
    @SerializedName("Step 3")
    val step_three: String?,
    @SerializedName("Step 4")
    val step_four: String?,
    @SerializedName("Step 5")
    val step_five: String?,
    @SerializedName("Step 6")
    val step_six: String?,
    @SerializedName("Step 7")
    val step_seven: String?,
    )
