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
        holder.txvCity.text = station.ville
        holder.txvAdress.text = station.adresse
        if(station.carburants.contains("Gazole")){station.prix.forEach {if (it.nom=="Gazole"){holder.txvPriceGazole.text = it.valeur.toString()+" €/L"}}}
        else{holder.txvPriceGazole.text = "Indisponible"}
        if(station.carburants.contains("SP98")){station.prix.forEach {if (it.nom=="SP98"){holder.txvPriceGazole.text = it.valeur.toString()+" €/L"}}}
        else{holder.txvPriceSP98.text = "Indisponible"}
        if(station.carburants.contains("E10")){station.prix.forEach {if (it.nom=="E10"){holder.txvPriceGazole.text = it.valeur.toString()+" €/L"}}}
        else{holder.txvPriceE10.text = "Indisponible"}
        if(station.carburants.contains("E85")){station.prix.forEach {if (it.nom=="E85"){holder.txvPriceGazole.text = it.valeur.toString()+" €/L"}}}
        else{holder.txvPriceE85.text = "Indisponible"}
        if(station.carburants.contains("SP95")){station.prix.forEach {if (it.nom=="SP95"){holder.txvPriceGazole.text = it.valeur.toString()+" €/L"}}}
        else{holder.txvPriceSP95.text = "Indisponible"}
        if(station.carburants.contains("GPLc")){station.prix.forEach {if (it.nom=="GPLc"){holder.txvPriceGazole.text = it.valeur.toString()+" €/L"}}}
        else{holder.txvPriceGPLc.text = "Indisponible"}
    }

    fun updateStations(allStations: List<Station>){
        stations = allStations
        notifyDataSetChanged()
    }
}