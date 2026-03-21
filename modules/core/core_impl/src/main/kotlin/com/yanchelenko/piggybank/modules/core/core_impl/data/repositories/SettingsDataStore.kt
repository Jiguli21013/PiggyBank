package com.yanchelenko.piggybank.modules.core.core_impl.data.repositories

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
//todo может все таки в модуль settings переместить
private const val DATASTORE_NAME = "settings"

val Context.settingsDataStore by preferencesDataStore(DATASTORE_NAME)
