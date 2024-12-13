package com.example.projetandroidfuel

class StationHM {
    private val storage = HashMap<Int,Station>()
    
    fun addStation(station: Station){
        storage[station.id] = station
    }

    fun getStation(id: Int): Station {
        return storage[id] ?: throw Exception("Station not found")
    }

    fun getAllStations(): ArrayList<Station> {
        return ArrayList(storage.values
            .sortedBy { station -> station.id })
    }

    fun getStationsWith(carburants: List<String>): List<Station> {
        return storage.filterValues { station -> station.carburants().containsAll(carburants) }
            .values
            .sortedBy { station -> station.id }
    }

    fun getTotalNumberOfStations(): Int {
        return storage.size
    }

    fun clear() {
        storage.clear()
    }
}