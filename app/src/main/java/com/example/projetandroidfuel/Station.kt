package com.example.projetandroidfuel

import java.io.Serializable

data class Station (
    val id: Int,
    val latitude: Int,
    val longitude: Int,
    val cp: Int,
    val adresse: String,
    val ville: String,
    val prix: List<PrixElement>,
    val carburants: List<String>,
    val favorite: Boolean
) : Serializable