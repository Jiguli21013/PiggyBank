package com.yanchelenko.piggybank.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.yanchelenko.piggybank.modules.core.core_api.navigation.dispatcher.NavigationDispatcher
import com.yanchelenko.piggynank.core.ui.theme.PiggyBankTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var navDispatcher: NavigationDispatcher //todo remove lateinit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { PiggyBankApp(navDispatcher = navDispatcher,) }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PiggyBankTheme {

    }
}
