package com.yanchelenko.piggybank.modules.core.core_impl.di

import com.yanchelenko.piggybank.modules.core.core_api.SettingsStorage
import com.yanchelenko.piggybank.modules.core.core_impl.data.repositories.SettingsStorageImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface SettingsStorageModule {

    @Binds
    @Singleton
    fun bindSettingsStorage(
        impl: SettingsStorageImpl
    ): SettingsStorage
}
