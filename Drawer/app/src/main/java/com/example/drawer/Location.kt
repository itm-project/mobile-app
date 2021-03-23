package com.example.drawer

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*
import kotlinx.android.synthetic.main.activity_location.*
import java.util.*


class Location : AppCompatActivity() {

    lateinit var session:sessionUser
    var PERMISSION_ID = 1000
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var locationRequest: LocationRequest


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)

        session = sessionUser(applicationContext)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

            Log.d("Debug:", CheckPermission().toString())
            Log.d("Debug:", isLocationEnabled().toString())
            RequestPermission()
            getLastLocation()

    }


    private fun getLastLocation(){
        if(CheckPermission()){
            if(isLocationEnabled()){
                //------------------------------------------------------
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
                //------------------------------------------------------
                fusedLocationProviderClient.lastLocation.addOnCompleteListener { task ->
                    var location = task.result
                    if(location == null)
                    {
                        NewLocationData()
                    }
                    else
                    {
                        tvLocation.text = "Your Current Coordinate are : \nLat: "+location.latitude+" ; Long: "+location.longitude
                        session.createLocationSession(location.latitude.toString(),location.longitude.toString())
                        var shareloma:HashMap<String,String> = session.getLocationDetail()
                        Log.e("TESTSESSIONINLOCATION","PROFRAG: "+shareloma)
                        }
                }
            }else{
                Toast.makeText(this,"Please Turn on Your device Location",Toast.LENGTH_SHORT).show()
            }
        }else{
            RequestPermission()
        }
    }

    private fun NewLocationData(){
        var locationRequest =  LocationRequest()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 0
        locationRequest.fastestInterval = 0
        locationRequest.numUpdates = 1
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
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
        fusedLocationProviderClient!!.requestLocationUpdates(locationRequest,locationCallback,Looper.myLooper())
    }


    private val locationCallback = object : LocationCallback(){
        override fun onLocationResult(locationResult: LocationResult) {
            var lastLocation: Location = locationResult.lastLocation
            Log.d("Debug:","your last last location: "+ lastLocation.longitude.toString())
            tvLocation.text = "You Last Location is : Long: "+ lastLocation.longitude + " , Lat: " + lastLocation.latitude + "\n"

        }
    }

    private fun CheckPermission():Boolean{
        if(
            ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
            ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
        ){
            return true
        }
        return false
    }



    private fun RequestPermission(){
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION,android.Manifest.permission.ACCESS_FINE_LOCATION), PERMISSION_ID
        )
    }

    private fun isLocationEnabled():Boolean{
        var locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode == PERMISSION_ID)
        {
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Log.d("Debug: ","YOU HAVE THE PERMISSION!")
            }
        }
    }

}

