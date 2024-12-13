package com.example.projetandroidfuel

import android.view.LayoutInflater
import android.view.View
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
        holder.txvCity.text = station.ville
        holder.txvAdress.text = station.adresse
        holder.txvFuels.text = station.fuelsString()
        if (station.favorite){
            holder.imgStarOn.visibility = View.VISIBLE
        }
        else{
            holder.imgStarOn.visibility = View.GONE
        }
    }

    fun updateStations(allStations: List<Station>){
        stations = allStations
        notifyDataSetChanged()
    }
}