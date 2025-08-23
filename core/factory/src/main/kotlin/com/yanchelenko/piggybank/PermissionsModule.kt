package com.yanchelenko.piggybank

import android.content.Context
import com.yanchelenko.piggybank.permissions.DefaultPermissionManager
import com.yanchelenko.piggybank.permissions.PermissionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object PermissionsModule {

    @Provides
    fun providePermissionManager(
        @ApplicationContext context: Context
    ): PermissionManager = DefaultPermissionManager(context)
}
