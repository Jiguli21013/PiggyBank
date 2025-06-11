package com.yanchelenko.permissions.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.runtime.remember
import com.yanchelenko.permissions.data.DefaultPermissionManager
import com.yanchelenko.permissions.domain.PermissionManager

@Composable
fun rememberPermissionManager(): PermissionManager {
    val context = LocalContext.current
    return remember { DefaultPermissionManager(context) }
}
