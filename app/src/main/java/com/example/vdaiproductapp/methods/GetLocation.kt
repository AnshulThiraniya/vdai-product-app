package com.example.vdaiproductapp.methods

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationListener
import android.location.LocationManager
import androidx.core.app.ActivityCompat

class GetLocation(val context:Context) {
    val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    val reqCode=1001
    var lat = 0.0
    var long=0.0
    var myList = ArrayList<Double>()
    fun getlocation():ArrayList<Double>{

        if (ActivityCompat.checkSelfPermission(context,android.Manifest.permission.ACCESS_FINE_LOCATION)
            ==PackageManager.PERMISSION_GRANTED){

         locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,100,5f,
             LocationListener {
               location ->
                  lat = location.latitude
                  long=location.longitude

             })
            myList.add(0,lat)
            myList.add(1,long)

            return myList
        }
        else{
            ActivityCompat.requestPermissions(context as Activity, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION),reqCode)

            return myList
        }
    }


}