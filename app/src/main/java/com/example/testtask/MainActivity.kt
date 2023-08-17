package com.example.testtask

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.SearchView.OnQueryTextListener
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testtask.Adapter.ForecastListAdapter
import com.example.testtask.databinding.ActivityMainBinding
import com.example.testtask.network.api.RetrofitFactory

import com.example.testtask.network.api.WeatherApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var forecastDayModelAdapter: ForecastListAdapter


    private val vm: WeatherViewModel by lazy {
        ViewModelProvider(this)[WeatherViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupRecyclerView()
        vm.getData()


        vm.data.observe(this) { model ->
            forecastDayModelAdapter.submitList(model.forecast.forecastday)
        }
        vm.status.observe(this) { state ->
            when (state) {
                ApiState.FAILURE -> {
                    binding.apply {
                        request.visibility = View.VISIBLE
                        rv.visibility = View.GONE
                        progress.visibility = View.GONE
                        tvError.visibility = View.VISIBLE
                    }
                }
                ApiState.LOADING -> {
                    binding.apply {
                        request.visibility = View.GONE
                        progress.visibility = View.VISIBLE
                        tvError.visibility = View.GONE
                        rv.visibility = View.GONE
                    }
                }
                ApiState.SUCCESS -> {
                    binding.apply {
                        request.visibility = View.GONE
                        progress.visibility = View.GONE
                        tvError.visibility = View.GONE
                        rv.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    private fun setupRecyclerView() {
        forecastDayModelAdapter = ForecastListAdapter(this)
        binding.rv.adapter = forecastDayModelAdapter
    }
}




