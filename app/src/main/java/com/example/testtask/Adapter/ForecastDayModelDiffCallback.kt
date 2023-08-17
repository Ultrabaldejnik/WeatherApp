package com.example.testtask.Adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.testtask.network.models.ForecastDayModel


class ForecastDayModelDiffCallback : DiffUtil.ItemCallback<ForecastDayModel>() {
    override fun areItemsTheSame(oldItem: ForecastDayModel, newItem: ForecastDayModel): Boolean {
        return oldItem.date == newItem.date
    }

    override fun areContentsTheSame(oldItem: ForecastDayModel, newItem: ForecastDayModel): Boolean {
        return oldItem == newItem
    }
}