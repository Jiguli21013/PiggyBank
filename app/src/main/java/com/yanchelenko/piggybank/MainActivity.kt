package com.yanchelenko.piggybank

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.yanchelenko.piggybank.modules.core.core_impl.presentation.AppViewModel
import com.yanchelenko.piggynank.core.ui.theme.PiggyBankTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val appViewModel: AppViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState = savedInstanceState)
        enableEdgeToEdge()
        setContent { PiggyBankApp(navDispatcher = appViewModel.navDispatcher) }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() { PiggyBankTheme {} }
