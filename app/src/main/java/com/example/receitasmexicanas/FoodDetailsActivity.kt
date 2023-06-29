package com.example.receitasmexicanas

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.comidamexicana.databinding.ActivityFoodDetailsBinding
import com.example.receitasmexicanas.modelResponse.FoodDetailsResponse
import com.example.receitasmexicanas.ultils.Constants

class FoodDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFoodDetailsBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodDetailsBinding.inflate(layoutInflater).apply { setContentView(root) }
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        sharedPreferences = getSharedPreferences(Constants.SAVE_ID, Context.MODE_PRIVATE)
        val id = sharedPreferences.getInt(Constants.FOOD_ID, 0)
        viewModel.getFoodByIs(id)


        observer()

    }

    private fun observer(){
        viewModel.getFoodByIdSuccess.observe(this){
            initInfos(it)
        }

        viewModel.getFoodByIdError.observe(this){

            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()

        }
    }

    private fun initInfos(infos: FoodDetailsResponse){
        Glide.with(this)
            .load(infos.image)
            .into(binding.imageStore)
        binding.textTitle.text = infos.description
        binding.textTime.text = infos.time

    }



}