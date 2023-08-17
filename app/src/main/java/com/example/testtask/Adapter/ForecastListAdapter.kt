package com.example.testtask.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testtask.R
import com.example.testtask.databinding.DayItemBinding
import com.example.testtask.network.models.ForecastDayModel
import com.squareup.picasso.Picasso

class ForecastListAdapter(private val context: Context) :
    ListAdapter<ForecastDayModel, ForecastListAdapter.ForecastDayModelViewHolder>(
        ForecastDayModelDiffCallback()
    ) {
    class ForecastDayModelViewHolder(val binding: DayItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastDayModelViewHolder {
        return ForecastDayModelViewHolder(
            DayItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ForecastDayModelViewHolder, position: Int) {
        val dayItem = getItem(position)

        with(holder.binding){
            tvData.text = dayItem.date
            tvAvghumidity.text = context.resources.getString(R.string.avghumidity,dayItem.day.avghumidity.toString())
            tvAvgtempC.text = dayItem.day.avgtemp_c.toString()
            tvMaxwindKph.text = context.resources.getString(R.string.maxwind_kph, dayItem.day.maxwind_kph.toString())
            Picasso.get().load("https:" + dayItem.day.condition.icon).into(imageWeather)
            condition.text = context.resources.getString(R.string.condition,dayItem.day.condition.text)
        }
    }
}