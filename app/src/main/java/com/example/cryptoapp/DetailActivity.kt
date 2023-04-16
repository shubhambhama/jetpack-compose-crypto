package com.example.cryptoapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.cryptoapp.data.db.Crypto
import com.example.cryptoapp.screens.CryptoDetailScreen
import com.example.cryptoapp.ui.theme.CryptoAppTheme

class DetailActivity : ComponentActivity() {
    val crypto by lazy {
        intent.getSerializableExtra(CRYPTO) as Crypto
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptoAppTheme {
                CryptoDetailScreen(crypto = crypto)
            }
        }
    }

    companion object {
        const val CRYPTO = "crypto"
        fun newIntent(context: Context, crypto: Crypto) =
                Intent(context, DetailActivity::class.java).apply {
                    putExtra(CRYPTO, crypto)
                }
    }
}