package com.yanchelenko.piggybank

import com.yanchelenko.piggybank.navigation.dispatcher.NavigationDispatcher
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.yanchelenko.piggynank.core.ui.theme.PiggyBankTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject lateinit var navDispatcher: NavigationDispatcher

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PiggyBankApp(
                navDispatcher = navDispatcher
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PiggyBankTheme {

    }
}
