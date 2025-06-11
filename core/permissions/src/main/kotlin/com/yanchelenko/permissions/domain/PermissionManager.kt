package com.yanchelenko.permissions.domain

interface PermissionManager {
    fun hasPermission(permission: String): Boolean
    fun shouldShowRationale(permission: String): Boolean
}
