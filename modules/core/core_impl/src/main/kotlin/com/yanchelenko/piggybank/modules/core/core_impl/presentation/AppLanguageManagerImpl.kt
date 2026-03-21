package com.yanchelenko.piggybank.modules.core.core_impl.presentation

import android.app.LocaleManager
import android.content.Context
import android.os.Build
import android.os.LocaleList
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.yanchelenko.piggybank.modules.core.core_api.domain.settings.AppLanguageManager
import com.yanchelenko.piggybank.modules.core.core_api.models.settings.AppLanguage
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AppLanguageManagerImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : AppLanguageManager {

    override fun apply(language: AppLanguage) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val localeManager = context.getSystemService(LocaleManager::class.java)
            localeManager.applicationLocales = when (language) {
                AppLanguage.SYSTEM -> LocaleList.getEmptyLocaleList()
                AppLanguage.ENGLISH -> LocaleList.forLanguageTags("en")
                AppLanguage.RUSSIAN -> LocaleList.forLanguageTags("ru")
            }
        } else {
            val locales = when (language) {
                AppLanguage.SYSTEM -> LocaleListCompat.getEmptyLocaleList()
                AppLanguage.ENGLISH -> LocaleListCompat.forLanguageTags("en")
                AppLanguage.RUSSIAN -> LocaleListCompat.forLanguageTags("ru")
            }
            AppCompatDelegate.setApplicationLocales(locales)
        }
    }
}
