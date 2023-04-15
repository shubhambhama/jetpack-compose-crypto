package com.example.cryptoapp.util

import android.content.Context
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun LottieLoadingView(context: Context, file: String, iteration : Int = 10, modifier: Modifier = Modifier) {
    val composition by rememberLottieComposition(LottieCompositionSpec.Asset(file))
    LottieAnimation(composition, iterations = iteration, modifier = modifier.defaultMinSize(300.dp))
}

@Composable
fun CryptoLoadingView(context: Context) {
    LottieLoadingView(context = context, file = "cryptoload.json", modifier = Modifier.fillMaxWidth().height(150.dp))
}