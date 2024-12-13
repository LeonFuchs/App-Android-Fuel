package com.example.projetandroidfuel

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StationViewHolder(rootView: View): RecyclerView.ViewHolder(rootView) {
    var txvCity = rootView.findViewById<TextView>(R.id.txvCity)
    var txvAdress = rootView.findViewById<TextView>(R.id.txvAdress)
    var txvFuels = rootView.findViewById<TextView>(R.id.txvFuels)
    var imgStarOn = rootView.findViewById<ImageView>(R.id.imgStarOn)
}
