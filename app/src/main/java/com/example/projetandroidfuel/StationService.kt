package com.example.projetandroidfuel

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface StationService {
    @GET("/fuels")
    fun getAllStations(): Call<List<Station>>

    @GET("/fuels")
    fun getStationsWith(@Query("carburant") carburant: String): Call<List<Station>>

    //TODO complete other HTTP requests
}