package com.example.testtask

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtask.network.models.WeatherDataModel
import com.example.testtask.network.api.RetrofitFactory
import com.example.testtask.network.models.ForecastDayModel
import kotlinx.coroutines.launch


enum class ApiState{LOADING,SUCCESS,FAILURE}
class WeatherViewModel : ViewModel() {

    private var _status = MutableLiveData<ApiState>()
    val status : LiveData<ApiState>
        get() = _status

    private var _data = MutableLiveData<WeatherDataModel>()
    val data : LiveData<WeatherDataModel>
        get() = _data

    fun getData(){
        _status.value = ApiState.LOADING
        viewModelScope.launch {
            try {
                _data.value = RetrofitFactory.makeRetrofitService().getWeatherData(
                    key = API_KEY,
                    q = "Kemerovo",
                    aqi = "no",
                    days = "5",
                    alerts = "no"
                )
                _status.value = ApiState.SUCCESS


            }catch (e : Exception){
                _status.value = ApiState.FAILURE

            }
        }
    }

    companion object {
        private const val API_KEY = "eb3991ef47044745a16102252231408"
    }
}