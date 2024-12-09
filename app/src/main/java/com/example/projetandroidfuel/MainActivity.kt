package com.example.projetandroidfuel

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

const val SERVER_BASE_URL = "https://app-29cf3f13-cafb-432e-9b23-f0010d073522.cleverapps.io/"

class MainActivity : AppCompatActivity() {

    private val stations = StationHM()

    private val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
        .baseUrl(SERVER_BASE_URL).build()
    private val stationService = retrofit.create(StationService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        stationService.getAllStations().enqueue(object : Callback<List<Station>> {
            override fun onResponse(
                call: Call<List<Station>>, response: Response<List<Station>>
            ) {
                val allBooks: List<Station>? = response.body()
                allBooks?.forEach { stations.addBook(it) }
                displayStationListFragment()
            }

            override fun onFailure(call: Call<List<Station>>, t: Throwable) {
                Toast.makeText(baseContext, "Failure MainActivity.onCreate stationService.getAllStations().enqueue", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun displayStationListFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val stationListFragment = StationListFragment.newInstance(stations.getAllStations())
        fragmentTransaction.replace(R.id.a_main_lyt_container, stationListFragment)
        fragmentTransaction.commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_delete -> {
                stations.clear()
                displayStationListFragment()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}