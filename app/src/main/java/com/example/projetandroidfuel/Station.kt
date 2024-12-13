package com.example.projetandroidfuel

import java.io.Serializable

data class Station (
    val id: Int,
    val latitude: Double,
    val longitude: Double,
    val cp: String,
    val adresse: String,
    val ville: String,
    val gazole_prix: String?,
    val sp95_prix: String?,
    val e85_prix: String?,
    val gplc_prix: String?,
    val e10_prix: String?,
    val sp98_prix: String?,
    var favorite: Boolean
) : Serializable
{
    fun toSnippet(): String {
        var res = ""
        res = this.fuelsString()
        return res
    }
    fun fuelsString(): String {
        var res = ""
        this.carburants().forEach {
            res += it + ", "
        }
        return res.dropLast(2)
    }
    fun carburants(): List<String> {
        val map = mutableMapOf<String,String?>()
        map["Gazole"] = gazole_prix
        map["sp95"] = sp95_prix
        map["e85"] = e85_prix
        map["GPLc"] = gplc_prix
        map["e10"] = e10_prix
        map["sp98"] = sp98_prix
        return map.filterValues { it!=null}
            .keys.toList()
    }
}