package com.yanchelenko.piggybank.permissions

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import javax.inject.Inject

class DefaultPermissionManager @Inject constructor(
    private val context: Context
) : PermissionManager {

    override fun hasPermission(permission: String): Boolean =
        ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED

    override fun shouldShowRationale(permission: String): Boolean =
        context is Activity && ActivityCompat.shouldShowRequestPermissionRationale(
            context,
            permission
        )
}
