package com.example.projetandroidfuel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class StationAdapter(private var stations: List<Station>): RecyclerView.Adapter<StationViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StationViewHolder {
        val rowView = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_station, parent, false)
        return StationViewHolder(rowView)
    }

    override fun getItemCount(): Int {
        return stations.size
    }

    override fun onBindViewHolder(holder: StationViewHolder, position: Int) {
        val station = stations[position]
        //TODO complete with adapted following
        //holder.txvElement.text = ElementString
    }

    fun updateStations(allStations: List<Station>){
        stations = allStations
        notifyDataSetChanged()
    }
}