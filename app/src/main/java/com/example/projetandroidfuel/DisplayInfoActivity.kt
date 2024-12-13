package com.example.projetandroidfuel

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import coil3.load
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val FAVORITE = "favorite"

class DisplayInfoActivity : AppCompatActivity() {

    private val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
        .baseUrl(SERVER_BASE_URL).build()
    private val stationService = retrofit.create(StationService::class.java)

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_info)

        val station = intent.getSerializableExtra("STATION") as Station

        this.findViewById<TextView>(R.id.txvCity).text = station.ville
        this.findViewById<TextView>(R.id.txvAdress).text = station.adresse

        this.findViewById<ImageView>(R.id.icoGazole).visibility = View.INVISIBLE
        this.findViewById<ImageView>(R.id.icoSP98).visibility = View.INVISIBLE
        this.findViewById<ImageView>(R.id.icoE10).visibility = View.INVISIBLE
        this.findViewById<ImageView>(R.id.icoE85).visibility = View.INVISIBLE
        this.findViewById<ImageView>(R.id.icoSP95).visibility = View.INVISIBLE
        this.findViewById<ImageView>(R.id.icoGPLc).visibility = View.INVISIBLE
        station.carburants.forEach {
            if (it=="Gazole"){
                this.findViewById<ImageView>(R.id.icoGazole).load("https://i.ibb.co/fdkc2Mj/IconGazole.jpg")
                this.findViewById<ImageView>(R.id.icoGazole).visibility = View.VISIBLE}
            if (it=="SP98"){
                this.findViewById<ImageView>(R.id.icoSP98).load("https://i.ibb.co/jZB6MJt/IconE10.jpg")
                this.findViewById<ImageView>(R.id.icoSP98).visibility = View.VISIBLE}
            if (it=="E10"){
                this.findViewById<ImageView>(R.id.icoE10).load("https://i.ibb.co/4g9mT1k/IconE10.jpg")
                this.findViewById<ImageView>(R.id.icoE10).visibility = View.VISIBLE}
            if (it=="E85"){
                this.findViewById<ImageView>(R.id.icoE85).load("https://i.ibb.co/KyPq04H/IconE85.jpg")
                this.findViewById<ImageView>(R.id.icoE85).visibility = View.VISIBLE}
            if (it=="SP95"){
                this.findViewById<ImageView>(R.id.icoSP95).load("https://i.ibb.co/drZL8kp/IconSP95.jpg")
                this.findViewById<ImageView>(R.id.icoSP95).visibility = View.VISIBLE}
            if (it=="GPLc"){
                this.findViewById<ImageView>(R.id.icoGPLc).load("https://i.ibb.co/FWycxVR/IconGPLc.jpg")
                this.findViewById<ImageView>(R.id.icoGPLc).visibility = View.VISIBLE}
        }

        if(station.carburants.contains("Gazole")){station.prix.forEach {
            if (it.nom=="Gazole"){this.findViewById<TextView>(R.id.txv_PriceGazole).text = it.valeur.toString()+" €/L"}}}
        else{this.findViewById<TextView>(R.id.txv_PriceGazole).text = "Indisponible"}
        if(station.carburants.contains("SP98")){station.prix.forEach {
            if (it.nom=="SP98"){this.findViewById<TextView>(R.id.txv_PriceSP98).text = it.valeur.toString()+" €/L"}}}
        else{this.findViewById<TextView>(R.id.txv_PriceSP98).text = "Indisponible"}
        if(station.carburants.contains("E10")){station.prix.forEach {
            if (it.nom=="E10"){this.findViewById<TextView>(R.id.txv_PriceE10).text = it.valeur.toString()+" €/L"}}}
        else{this.findViewById<TextView>(R.id.txv_PriceE10).text = "Indisponible"}
        if(station.carburants.contains("E85")){station.prix.forEach {
            if (it.nom=="E85"){this.findViewById<TextView>(R.id.txv_PriceE85).text = it.valeur.toString()+" €/L"}}}
        else{this.findViewById<TextView>(R.id.txv_PriceE85).text = "Indisponible"}
        if(station.carburants.contains("SP95")){station.prix.forEach {
            if (it.nom=="SP95"){this.findViewById<TextView>(R.id.txv_PriceSP95).text = it.valeur.toString()+" €/L"}}}
        else{this.findViewById<TextView>(R.id.txv_PriceSP95).text = "Indisponible"}
        if(station.carburants.contains("GPLc")){station.prix.forEach {
            if (it.nom=="GPLc"){this.findViewById<TextView>(R.id.txv_PriceGplc).text = it.valeur.toString()+" €/L"}}}
        else{this.findViewById<TextView>(R.id.txv_PriceGplc).text = "Indisponible"}


        val favoriteCBox = findViewById<CheckBox>(R.id.cBoxFavorite)
        favoriteCBox.isChecked = station.favorite
        favoriteCBox.setOnCheckedChangeListener { _, _ -> station.favorite = !station.favorite }
        saveFavorite(station)
    }

    private fun saveFavorite(station: Station) {
        stationService.putFavorite(station.id,station.favorite).enqueue(object : Callback<Station> {
            override fun onResponse(call: Call<Station>, response: Response<Station>) {
                if (response.isSuccessful) {
                    response.body()?.let {station.favorite = it.favorite}
                    Toast.makeText(baseContext, "Success put favorite", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Station>, t: Throwable) {
                Toast.makeText(baseContext, "Failure put favorite", Toast.LENGTH_SHORT).show()
            }
        })
    }
}