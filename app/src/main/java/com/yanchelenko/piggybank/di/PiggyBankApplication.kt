package com.yanchelenko.piggybank.di

import android.app.Application
import android.util.Log
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@HiltAndroidApp
class PiggyBankApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val entryPoint = EntryPointAccessors.fromApplication(
            context = this,
            entryPoint = AppLanguageEntryPoint::class.java
        )

        val observeLanguage = entryPoint.observeAppLanguageUseCase()
        val manager = entryPoint.appLanguageManager()

        CoroutineScope(context = SupervisorJob() + Dispatchers.Main).launch {  // todo so?
            observeLanguage()
                .distinctUntilChanged()
                .collect { language ->
                    manager.apply(language = language)
                    Log.d("PiggyBankApplication", "collect language=$language") //todo code style
                }
        }
    }
}
