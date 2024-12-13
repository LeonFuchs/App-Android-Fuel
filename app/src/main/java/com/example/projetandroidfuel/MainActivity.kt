package com.example.projetandroidfuel

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val SERVER_BASE_URL = "https://app-29cf3f13-cafb-432e-9b23-f0010d073522.cleverapps.io/"

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private val stations = StationHM()

    private val btnListDisplay: Button by lazy { findViewById(R.id.buttonList) }
    private val btnMapDisplay: Button by lazy { findViewById(R.id.buttonMap) }
    private val btnInfoDisplay: Button by lazy { findViewById(R.id.buttonInfo) }

    private val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
        .baseUrl(SERVER_BASE_URL).build()
    private val stationService = retrofit.create(StationService::class.java)

    private val supportMapFragment = SupportMapFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        stationService.getAllStations().enqueue(object : Callback<List<Station>> {
            override fun onResponse(
                call: Call<List<Station>>, response: Response<List<Station>>
            ) {
                val allBooks: List<Station>? = response.body()
                allBooks?.forEach { stations.addStation(it) }
                Toast.makeText(baseContext, "Import Success", Toast.LENGTH_SHORT).show()
                Toast.makeText(baseContext, allBooks?.size.toString(), Toast.LENGTH_SHORT).show()
                displayStationListFragment()
            }

            override fun onFailure(call: Call<List<Station>>, t: Throwable) {
                Toast.makeText(baseContext, "Failure MainActivity.onCreate stationService.getAllStations().enqueue", Toast.LENGTH_SHORT).show()
            }
        })
        stations.addStation(Station(1,4200000,600000,"28124","45 route bla","Gard",listOf(PrixElement("Gazole","1","02-12-2024","1.952"),PrixElement("SP95","2","25-12-2024","0.530")), listOf("Gazole","SP95"),true))

        btnListDisplay.setOnClickListener { displayStationListFragment() }
        btnMapDisplay.setOnClickListener { displayMapFragment() }
        btnInfoDisplay.setOnClickListener { displayGeneralInfoFragment() }

        supportFragmentManager.beginTransaction()
            .add(R.id.a_main_lyt_container, supportMapFragment)
            .commit()
        supportMapFragment.getMapAsync(this)

        displayStationListFragment()
    }

    private fun displayStationListFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val stationListFragment = StationListFragment.newInstance(stations.getAllStations())
        fragmentTransaction.replace(R.id.a_main_lyt_container, stationListFragment)
        fragmentTransaction.commit()
    }

    private fun displayGeneralInfoFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.a_main_lyt_container, GeneralInfoFragment())
        fragmentTransaction.commit()
    }

    private fun displayMapFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.a_main_lyt_container, SupportMapFragment())
        fragmentTransaction.commit()
        supportMapFragment.getMapAsync(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_refresh -> {
                stationService.getAllStations().enqueue(object : Callback<List<Station>> {
                    override fun onResponse(
                        call: Call<List<Station>>,
                        response: Response<List<Station>>
                    ) {
                        val allBooks: List<Station>? = response.body()
                        allBooks?.forEach { stations.addStation(it) }
                        Toast.makeText(baseContext, "Success refresh data", Toast.LENGTH_SHORT).show()
                        Toast.makeText(baseContext, allBooks?.size.toString(), Toast.LENGTH_SHORT).show()
                        displayStationListFragment()
                    }

                    override fun onFailure(call: Call<List<Station>>, t: Throwable) {
                        Toast.makeText(baseContext, "Failure refresh data", Toast.LENGTH_SHORT).show()
                    }
                })
                displayStationListFragment()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onMapReady(map: GoogleMap) {
        //TODO remove gardanne
        val gardanne = LatLng(43.450001,5.466667)
        map.addMarker(
            MarkerOptions()
            .position(gardanne)
            .title("Gardanne")
            .snippet("ISMIN")
        )
        map.moveCamera(CameraUpdateFactory.zoomTo(4F))
        map.moveCamera(CameraUpdateFactory.newLatLng(gardanne))
        stations.getAllStations().forEach{
            map.addMarker(
                MarkerOptions()
                    .position(LatLng(it.latitude.toDouble()/100000,it.longitude.toDouble()/100000))
                    .title(it.carburants.joinToString(","))
                    .snippet(it.toSnippet())
            )
        }
    }
}