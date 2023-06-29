package com.example.receitasmexicanas.retrofit


import com.example.receitasmexicanas.service.FoodService
import com.example.receitasmexicanas.ultils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class RetrofitConfiguration {


    companion object {

        private lateinit var retrofit: Retrofit
        private const val baseUrlFood = Constants.BASE_URL
        private const val CONNECTION_TIMEOUT = 20 * 1000


        private fun getRetrofitInstance(): Retrofit {
            val client = OkHttpClient.Builder().apply {
                addInterceptor { chain ->
                    val newRequest = chain.request().newBuilder()
                        .header("Content-type", "application/json")
                        .header("X-RapidAPI-Key", Constants.API_KEY)
                        .build()
                    chain.proceed(newRequest)
                }
                connectTimeout(CONNECTION_TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
                readTimeout(1, TimeUnit.MINUTES)
            }.build()

            if (!Companion::retrofit.isInitialized) {
                retrofit = Retrofit.Builder()
                    .baseUrl(baseUrlFood).client(client)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create()).build()

            }

            return retrofit

        }

        fun createFoodApiService(): FoodService {
            val retrofit = getRetrofitInstance()
            return retrofit.create(FoodService::class.java)
        }
    }
}