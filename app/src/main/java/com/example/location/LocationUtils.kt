package com.example.location

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class LocationUtils(private val context: Context) {

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