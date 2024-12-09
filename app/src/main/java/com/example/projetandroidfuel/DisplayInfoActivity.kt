package com.example.projetandroidfuel

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

val FAVORITE = "favorite"

class DisplayInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_info)

        //TODO complete detailed info display
    }

    private fun saveFavorite() {
        //TODO get favorite state
        //val favorite =
        intent.putExtra(FAVORITE, favorite)
        setResult(RESULT_OK, intent)
        finish()
    }
}