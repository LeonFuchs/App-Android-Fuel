package com.example.projetandroidfuel

import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface StationService {
    @GET("/stations")
    fun getAllStations(): Call<List<Station>>

    @PUT("/stations")
    fun putFavorite(@Query("id") id: Int,@Body favorite: Boolean)
}