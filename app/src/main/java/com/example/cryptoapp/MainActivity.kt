package com.example.cryptoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cryptoapp.data.db.Crypto
import com.example.cryptoapp.screens.HomeScreen
import com.example.cryptoapp.screens.HomeScreenViewModel
import com.example.cryptoapp.screens.HomeScreenViewModelFactory
import com.example.cryptoapp.ui.theme.CryptoAppTheme

sealed class InteractionsEvent {
    data class OpenDetailScreen(val crypto: Crypto) : InteractionsEvent()
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptoAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background) {
                    val viewModel: HomeScreenViewModel = viewModel(
                            factory = HomeScreenViewModelFactory(
                                    LocalContext.current))
                    HomeScreen(interactionsEvent = {
                        handleInteractionEvents(it /*viewModel*/)
                    })
                }
            }
        }
    }

    private fun handleInteractionEvents(
            interactionsEvent: InteractionsEvent,
//                                        viewModel: HomeScreenViewModel
    ) {
        when (interactionsEvent) {
            is InteractionsEvent.OpenDetailScreen -> {
                startActivity(
                        DetailActivity.newIntent(context = this, crypto = interactionsEvent.crypto))
            }
        }

    }
}