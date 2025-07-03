package com.yanchelenko.piggybank.modules.core.core_api.permissions

interface PermissionManager {
    fun hasPermission(permission: String): Boolean
    fun shouldShowRationale(permission: String): Boolean
}