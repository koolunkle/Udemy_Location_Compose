package com.example.location

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

class LocationUtils(val context: Context) {

    fun hasLocationPermission(context: Context): Boolean =
        context.hasPermission(Manifest.permission.ACCESS_FINE_LOCATION) && context.hasPermission(
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
}

fun Context.hasPermission(permission: String): Boolean =
    ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED