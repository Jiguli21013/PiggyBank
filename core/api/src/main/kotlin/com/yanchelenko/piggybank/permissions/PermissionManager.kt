package com.yanchelenko.piggybank.permissions

interface PermissionManager {
    fun hasPermission(permission: String): Boolean
    fun shouldShowRationale(permission: String): Boolean
}