package com.example.projetandroidfuel

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

val FAVORITE = "favorite"

class DisplayInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_info)

        //TODO complete detailed info display
        /*
        var txvPriceGazole = rootView.findViewById<TextView>(R.id.txv_PriceGazole)
    var txvPriceSP98 = rootView.findViewById<TextView>(R.id.txv_PriceSP98)
    var txvPriceE10 = rootView.findViewById<TextView>(R.id.txv_PriceE10)
    var txvPriceE85 = rootView.findViewById<TextView>(R.id.txv_PriceE85)
    var txvPriceSP95 = rootView.findViewById<TextView>(R.id.txv_PriceSP95)
    var txvPriceGPLc = rootView.findViewById<TextView>(R.id.txv_PriceGplc)

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
         */
    }

    private fun saveFavorite() {
        //TODO get favorite state
        //val favorite =
        //intent.putExtra(FAVORITE, favorite)
        setResult(RESULT_OK, intent)
        finish()
    }
}