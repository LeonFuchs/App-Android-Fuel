package com.example.projetandroidfuel

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StationViewHolder(rootView: View): RecyclerView.ViewHolder(rootView) {
    var txvCity = rootView.findViewById<TextView>(R.id.txvCity)
    var txvAdress = rootView.findViewById<TextView>(R.id.txvAdress)
    var txvPriceGazole = rootView.findViewById<TextView>(R.id.txv_PriceGazole)
    var txvPriceSP98 = rootView.findViewById<TextView>(R.id.txv_PriceSP98)
    var txvPriceE10 = rootView.findViewById<TextView>(R.id.txv_PriceE10)
    var txvPriceE85 = rootView.findViewById<TextView>(R.id.txv_PriceE85)
    var txvPriceSP95 = rootView.findViewById<TextView>(R.id.txv_PriceSP95)
    var txvPriceGPLc = rootView.findViewById<TextView>(R.id.txv_PriceGplc)
}
