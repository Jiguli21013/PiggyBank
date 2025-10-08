package com.yanchelenko.piggybank.modules.core.core_factory

import android.content.Context
import com.yanchelenko.piggybank.modules.core.core_api.permissions.PermissionManager
import com.yanchelenko.piggybank.modules.core.core_impl.permissions.DefaultPermissionManager
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
