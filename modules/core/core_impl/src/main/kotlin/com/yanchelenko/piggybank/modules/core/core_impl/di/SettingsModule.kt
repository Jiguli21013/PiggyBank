package com.yanchelenko.piggybank.modules.core.core_impl.di

import com.yanchelenko.piggybank.modules.core.core_api.repository.SettingsRepository
import com.yanchelenko.piggybank.modules.core.core_impl.data.repositories.SettingsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SettingsModule {

    @Binds
    abstract fun bindSettingsRepository(
        impl: SettingsRepositoryImpl
    ): SettingsRepository
}
