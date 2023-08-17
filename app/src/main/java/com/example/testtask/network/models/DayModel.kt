package com.example.testtask.network.models

data class DayModel(
    val condition: ConditionModel,
    val avgtemp_c: Float,
    val maxwind_kph: Float,
    val avghumidity: Float,
)