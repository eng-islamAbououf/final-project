package com.finalProject.myapplication.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentActivity
import com.finalProject.myapplication.R
import com.finalProject.myapplication.show
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.Polyline
import com.google.android.gms.maps.model.PolylineOptions


class MapActivity : FragmentActivity(), OnMapReadyCallback{

    lateinit var map : GoogleMap
    lateinit var supportMapFragment : SupportMapFragment

    var myLocationLat : Double = 0.0
    var myLocationLng : Double = 0.0
    var desLocationLat : Double = 0.0
    var desLocationLng : Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        supportMapFragment = supportFragmentManager.findFragmentById(R.id.frag_map) as SupportMapFragment
        supportMapFragment.getMapAsync(this)
    }


    override fun onBackPressed() {
        super.onBackPressed()
        goToMain()
    }

    fun goToMain(){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        myLocationLat  = intent.getDoubleExtra("myLocationLat",0.0)
        myLocationLng  = intent.getDoubleExtra("myLocationLng",0.0)
        desLocationLat = intent.getDoubleExtra("desLocationLat",0.0)
        desLocationLng = intent.getDoubleExtra("desLocationLng",0.0)
        if (googleMap != null) {
            map=googleMap
        }
        map.mapType=GoogleMap.MAP_TYPE_NORMAL

        // Add a marker in myLocation and move the camera
        val myLocation = LatLng(myLocationLat, myLocationLng)
        val destination = LatLng(desLocationLat,desLocationLng)
        map.addMarker(MarkerOptions().position(myLocation).title("Marker in My Location"))
        map.addMarker(MarkerOptions().position(destination).title("Destination Marker"))
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(destination, 10F))

        //check permision
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        map.isBuildingsEnabled = true
        map.isTrafficEnabled = true
        map.isMyLocationEnabled=true
        val options = PolylineOptions()
        options.color(Color.RED)
        options.width(10f)
        options.add(myLocation).add(destination)
        map.addPolyline(options)
    }
}


