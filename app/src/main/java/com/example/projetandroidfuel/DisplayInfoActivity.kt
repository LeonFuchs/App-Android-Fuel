package com.example.projetandroidfuel

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import coil3.load
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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


        if (station.gazole_prix!=null){
            this.findViewById<ImageView>(R.id.icoGazole).load("https://i.ibb.co/fdkc2Mj/IconGazole.jpg")
            this.findViewById<ImageView>(R.id.icoGazole).visibility = View.VISIBLE
            this.findViewById<TextView>(R.id.txv_PriceGazole).text = station.gazole_prix.toString()+" €/L"
        }else{this.findViewById<TextView>(R.id.txv_PriceGazole).text = "Indisponible"}
        if (station.sp98_prix!=null){
            this.findViewById<ImageView>(R.id.icoSP98).load("https://i.ibb.co/jZB6MJt/IconE10.jpg")
            this.findViewById<ImageView>(R.id.icoSP98).visibility = View.VISIBLE
            this.findViewById<TextView>(R.id.txv_PriceSP98).text = station.sp98_prix.toString()+" €/L"
        }else{this.findViewById<TextView>(R.id.txv_PriceSP98).text = "Indisponible"}
        if (station.e10_prix!=null){
            this.findViewById<ImageView>(R.id.icoE10).load("https://i.ibb.co/4g9mT1k/IconE10.jpg")
            this.findViewById<ImageView>(R.id.icoE10).visibility = View.VISIBLE
            this.findViewById<TextView>(R.id.txv_PriceE10).text = station.e10_prix.toString()+" €/L"
        }else{this.findViewById<TextView>(R.id.txv_PriceE10).text = "Indisponible"}
        if (station.e85_prix!=null){
            this.findViewById<ImageView>(R.id.icoE85).load("https://i.ibb.co/KyPq04H/IconE85.jpg")
            this.findViewById<ImageView>(R.id.icoE85).visibility = View.VISIBLE
            this.findViewById<TextView>(R.id.txv_PriceE85).text = station.e85_prix.toString()+" €/L"
        }else{this.findViewById<TextView>(R.id.txv_PriceE85).text = "Indisponible"}
        if (station.sp95_prix!=null){
            this.findViewById<ImageView>(R.id.icoSP95).load("https://i.ibb.co/drZL8kp/IconSP95.jpg")
            this.findViewById<ImageView>(R.id.icoSP95).visibility = View.VISIBLE
            this.findViewById<TextView>(R.id.txv_PriceSP95).text = station.sp95_prix.toString()+" €/L"
        }else{this.findViewById<TextView>(R.id.txv_PriceSP95).text = "Indisponible"}
        if (station.gplc_prix!=null){
            this.findViewById<ImageView>(R.id.icoGPLc).load("https://i.ibb.co/FWycxVR/IconGPLc.jpg")
            this.findViewById<ImageView>(R.id.icoGPLc).visibility = View.VISIBLE
            this.findViewById<TextView>(R.id.txv_PriceGplc).text = station.gplc_prix.toString()+" €/L"
        }else{this.findViewById<TextView>(R.id.txv_PriceGplc).text = "Indisponible"}


        val favoriteCBox = findViewById<CheckBox>(R.id.cBoxFavorite)
        favoriteCBox.isChecked = station.favorite
        favoriteCBox.setOnCheckedChangeListener { _, _ -> station.favorite = !station.favorite }
        saveFavorite(station)
    }

    private fun saveFavorite(station: Station) {
        stationService.putFavorite(station.id,station.favorite).enqueue(object : Callback<Station> {
            override fun onResponse(call: Call<Station>, response: Response<Station>) {
                if (response.isSuccessful) {
                    Toast.makeText(baseContext, "Success put favorite", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Station>, t: Throwable) {
                Toast.makeText(baseContext, "Failure put favorite", Toast.LENGTH_SHORT).show()
            }
        })
    }
}