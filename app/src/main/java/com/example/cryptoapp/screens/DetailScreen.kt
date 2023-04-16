package com.example.cryptoapp.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.cryptoapp.data.db.Crypto
import com.example.cryptoapp.ui.theme.green700
import com.example.cryptoapp.ui.theme.red500
import com.example.cryptoapp.util.roundToTwoDecimal

@Composable
fun CryptoDetailScreen(crypto: Crypto) {
    Scaffold {
        Box(Modifier.padding(it)) {
            val scrollState = rememberScrollState()
            Header(crypto = crypto, scrollState = scrollState)
        }
    }
}

@Composable
fun Header(crypto: Crypto, scrollState: ScrollState) {
    val context = LocalContext.current
    Column(modifier = Modifier
            .padding(12.dp)
            .alpha(animateFloatAsState(
                    targetValue = (1 - scrollState.value / 150)
                            .coerceIn(0, 1)
                            .toFloat()).value)) {
        Row(modifier = Modifier.padding(top = 20.dp)) {
            Text(text = crypto.name, style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(end = 8.dp))

            AsyncImage(model = ImageRequest.Builder(context).data(crypto.image).build(),
                    modifier = Modifier.size(28.dp), contentDescription = null)
        }
        Text(text = "${crypto.price.roundToTwoDecimal()} USD", style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.ExtraBold)
        Text(text = "${crypto.dailyChange.roundToTwoDecimal()} ${crypto.dailyChangePercentage.roundToTwoDecimal()}% Today",
                color = if (crypto.dailyChange > 0) green700
                else Color.Red,
                fontWeight = FontWeight.ExtraBold)
    }
}