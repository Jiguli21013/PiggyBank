package com.yanchelenko.permissions.di

import android.content.Context
import com.yanchelenko.permissions.data.DefaultPermissionManager
import com.yanchelenko.permissions.domain.PermissionManager
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
