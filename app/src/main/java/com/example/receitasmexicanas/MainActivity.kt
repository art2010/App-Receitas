package com.example.receitasmexicanas

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.toColor
import androidx.fragment.app.Fragment
import androidx.fragment.app.replace
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.comidamexicana.R
import com.example.comidamexicana.adicionar
import com.example.comidamexicana.databinding.ActivityMainBinding
import com.example.comidamexicana.databinding.FragmentPesquisaBinding
import com.example.comidamexicana.favoritos
import com.example.comidamexicana.pesquisa
import com.example.comidamexicana.usuario
import com.example.receitasmexicanas.modelResponse.FoodResponse
import com.example.receitasmexicanas.ultils.Constants

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var foodAdapter: FoodAdapter
    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var pesquisaFragment: pesquisa
    private lateinit var adicionarFragment: adicionar
    private lateinit var favoritosFragment: favoritos
    private lateinit var usuarioFragment: usuario

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(root) }
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.getFood()

        pesquisaFragment = pesquisa()
        adicionarFragment = adicionar()
        favoritosFragment = favoritos()
        usuarioFragment = usuario()


        //replaceFragment(pesquisaFragment)

        sharedPreferences = getSharedPreferences(Constants.SAVE_ID, Context.MODE_PRIVATE)

        observer()
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){

                R.id.pesquisa -> replaceFragment(pesquisa())
                R.id.add -> replaceFragment(adicionar())
                R.id.fav -> replaceFragment(favoritos())
                R.id.user -> replaceFragment(usuario())

                else->{

                }
            }
            true
        }

    }

    private fun observer() {
        viewModel.getFoodSuccess.observe(this) {
            initListFood(it)
        }

        viewModel.getFoodError.observe(this) {

            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()

        }


    }


    private fun initListFood(listFood: List<FoodResponse>) {
        binding.rvListFood.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            foodAdapter = FoodAdapter(listFood) { store ->
                val editor = sharedPreferences.edit()
                editor.putInt(Constants.FOOD_ID, store.id)
                editor.commit()
                editor.apply()
                val intent = Intent(context, FoodDetailsActivity::class.java)
                startActivity(intent)
            }
            adapter = foodAdapter
        }
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
    }






}