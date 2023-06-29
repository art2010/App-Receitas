package com.example.receitasmexicanas

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.receitasmexicanas.modelResponse.FoodDetailsResponse
import com.example.receitasmexicanas.modelResponse.FoodResponse
import com.example.receitasmexicanas.repository.FoodRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    val foodRepository = FoodRepository()
    val getFoodSuccess = MutableLiveData<List<FoodResponse>>()
    val getFoodByIdSuccess = MutableLiveData<FoodDetailsResponse>()
    val getFoodError = MutableLiveData<String>()
    val getFoodByIdError = MutableLiveData<String>()

    fun getFood() {
        val request = foodRepository.getFood()
        request.enqueue(object : Callback<List<FoodResponse>> {
            override fun onResponse(
                call: Call<List<FoodResponse>>,
                response: Response<List<FoodResponse>>
            ) {
                if (response.isSuccessful) {
                    getFoodSuccess.postValue(response.body())
                } else {
                    // tratar erro com o sucesso da chamada
                }
            }

            override fun onFailure(call: Call<List<FoodResponse>>, t: Throwable) {
                getFoodError.postValue(t.message)
            }
        })
    }

    fun getFoodByIs(id: Int) {
        val request = foodRepository.getFoodById(id)
        request.enqueue(object : Callback<FoodDetailsResponse> {
            override fun onResponse(
                call: Call<FoodDetailsResponse>,
                response: Response<FoodDetailsResponse>
            ) {
                if (response.isSuccessful) {
                    getFoodByIdSuccess.postValue(response.body())
                } else {
                    // tratar erro com o sucesso da chamada
                }
            }

            override fun onFailure(call: Call<FoodDetailsResponse>, t: Throwable) {
                getFoodByIdError.postValue(t.message)
            }
        })
    }
}