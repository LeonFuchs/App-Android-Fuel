package com.example.projetandroidfuel

import java.io.Serializable

data class Station (
    val id: Int,
    val latitude: Int,
    val longitude: Int,
    val cp: String,
    val adresse: String,
    val ville: String,
    val prix: List<PrixElement>,
    val carburants: List<String>,
    val favorite: Boolean
) : Serializable
{
    fun toSnippet(): String {
        var res = ""
        this.prix.forEach {
            res += it.nom + " : " + it.valeur + "\n"
        }
        return res
    }
}