package com.example.gebruiker.geoguessswipe

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

class GeoObjectAdapter(private val context: Context, var listGeoObject: List<GeoObject>) : RecyclerView.Adapter<GeoObjectAdapter.GeoObjectViewHolder>() {

    inner class GeoObjectViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        var geoImage: ImageView = view.findViewById(R.id.geoImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GeoObjectViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.grid, parent, false)
        return GeoObjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: GeoObjectViewHolder, position: Int) {
        val geoObject = listGeoObject[position]
        holder.geoImage.setImageResource(geoObject.getGeoImageName())

    }

    override fun getItemCount(): Int {
        return listGeoObject.size
    }
}

