package com.example.location

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Looper
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority

class LocationUtils(private val context: Context) {

    private val _fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission")
    fun requestLocationUpdates(viewModel: LocationViewModel) {
        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)
                locationResult.lastLocation?.let {
                    val location = LocationData(latitude = it.latitude, longitude = it.longitude)
                    viewModel.updateLocation(location)
                }
            }
        }
        val locationRequest = LocationRequest.Builder(
            Priority.PRIORITY_HIGH_ACCURACY, 1000L
        ).build()

        _fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper(),
        )
    }

    fun hasLocationPermission(): Boolean =
        context.hasPermission(Manifest.permission.ACCESS_FINE_LOCATION) && context.hasPermission(
            Manifest.permission.ACCESS_COARSE_LOCATION
        )

    fun shouldShowLocationRationale(): Boolean =
        context.shouldShowRationale(Manifest.permission.ACCESS_FINE_LOCATION) || context.shouldShowRationale(
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
}

fun Context.hasPermission(permission: String): Boolean =
    ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED

fun Context.shouldShowRationale(permission: String): Boolean =
    ActivityCompat.shouldShowRequestPermissionRationale(this as MainActivity, permission)