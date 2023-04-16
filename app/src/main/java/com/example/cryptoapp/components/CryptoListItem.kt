package com.example.cryptoapp.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.cryptoapp.InteractionsEvent
import com.example.cryptoapp.data.db.Crypto
import com.example.cryptoapp.ui.theme.gradientGreenColors
import com.example.cryptoapp.ui.theme.gradientRedColors
import com.example.cryptoapp.ui.theme.green500
import com.example.cryptoapp.ui.theme.red500
import com.example.cryptoapp.util.roundToThreeDecimal
import com.example.cryptoapp.util.roundToTwoDecimal

@Composable
fun CryptoListItem(crypto: Crypto, isFav: Boolean = false,
                   interactionsEvent: (InteractionsEvent) -> Unit) {
    val context = LocalContext.current
    Row(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable(onClick = { interactionsEvent(InteractionsEvent.OpenDetailScreen(crypto)) }),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
        AsyncImage(model = ImageRequest.Builder(context).data(crypto.image).build(),
                contentDescription = "",
                modifier = Modifier
                        .size(40.dp)
                        .padding(4.dp),
                contentScale = ContentScale.Crop)

        Column(modifier = Modifier.weight(0.3f)) {
            Text(text = "$${crypto.price}", style = MaterialTheme.typography.h6, fontSize = 12.sp,
                    modifier = Modifier.padding(8.dp))
        }

        Column(modifier = Modifier.weight(1.0f)) {
            LineChart(yAxis = crypto.chartData, modifier = Modifier
                    .width(130.dp)
                    .height(50.dp)
                    .align(Alignment.CenterHorizontally),
                    lineColors = if (crypto.dailyChange > 0) gradientGreenColors else gradientRedColors)
            Text(text = crypto.dailyChange.roundToThreeDecimal() + "(${crypto.dailyChangePercentage.roundToTwoDecimal()} %)",
                    style = MaterialTheme.typography.subtitle1,
                    color = if (crypto.dailyChange > 0) green500 else red500,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.End))
        }
        IconToggleButton(checked = isFav, onCheckedChange = {hasFav ->
            if (hasFav) {

            }
        }) {
            Icon(imageVector = Icons.Default.Favorite, contentDescription = null,
            tint = if (isFav) Color.Red else MaterialTheme.colors.onSurface, modifier = Modifier.size(24.dp))
        }
    }
}