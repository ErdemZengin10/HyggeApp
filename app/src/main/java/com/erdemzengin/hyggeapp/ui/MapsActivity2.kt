package com.erdemzengin.hyggeapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.erdemzengin.hyggeapp.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_detail.*

class MapsActivity2 : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps2)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        goBackButton.setOnClickListener {
            super.onBackPressed()
        }

    }


    override fun onMapReady(googleMap: GoogleMap) {
        val lat = intent.getStringExtra("lat")
        val lng= intent.getStringExtra("lng")
        var finalLat=lat?.toDouble()
        var finalLong=lng?.toDouble()

        mMap = googleMap


        val shopPosition = LatLng(finalLat!!,finalLong!!)
        mMap.addMarker(MarkerOptions().position(shopPosition).title("Open Shop"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(shopPosition,15f))
    }
}