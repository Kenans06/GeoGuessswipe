package com.example.gebruiker.geoguessswipe

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.support.v7.widget.helper.ItemTouchHelper
import android.widget.Toast
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mGeoRecyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val mGeoObjects = ArrayList<GeoObject>()
        val mAdapter = GeoObjectAdapter(this, mGeoObjects)

        for (i in GeoObject.GEO_OBJECT_NAMES.indices) {
            mGeoObjects.add(GeoObject(GeoObject.GEO_OBJECT_NAMES[i],
                    GeoObject.IMAGES[i]))

            val mLayoutManager = StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL)
            mGeoRecyclerView.layoutManager = mLayoutManager
            mGeoRecyclerView.adapter = mAdapter

        }
        val simpleItemTouchCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {

                val position = viewHolder.adapterPosition
                if (mGeoObjects[position].getGeoName()?.contains("yes_")!! &&
                        swipeDir == 1 shl 2 || mGeoObjects[position].getGeoName()?.contains("no_")!!
                        &&  swipeDir == 1 shl 3) {
                            Toast.makeText(baseContext, "Juiste Locatie!", Toast.LENGTH_SHORT).show()
                        } else {
                    Toast.makeText(baseContext, "Verkeerde locatie...", Toast.LENGTH_SHORT).show()
                }
                mGeoObjects.removeAt(position)
                mAdapter.notifyItemRemoved(position)
            }
        }

        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(mGeoRecyclerView)

    }
}
