package com.example.cryptoapp.util

import java.text.DecimalFormat

private val formatter2 = DecimalFormat("##.##")
private val formatter3 = DecimalFormat("##.###")


fun Double.roundToTwoDecimal() = formatter2.format(this).toString()
fun Double.roundToThreeDecimal() = formatter3.format(this).toString()