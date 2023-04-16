package com.example.cryptoapp.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import java.lang.StrictMath.min

@Composable
fun LineChart(modifier: Modifier = Modifier,
              lineColors: List<Color> = listOf(MaterialTheme.colors.primary,
                      MaterialTheme.colors.primary),
              lineWidth: Float = 4f,
              yAxis: List<Float>,
              shouldAnimate: Boolean = true,
              shouldDrawLiveDot: Boolean = true,
              animationKey: Any? = Unit,
              customXTarget: Int = 0) {
    val x = remember {
        Animatable(0f)
    }
    val xTarget = if (customXTarget > 0) customXTarget.toFloat() else (yAxis.size - 1).toFloat()
    LaunchedEffect(animationKey) {
        x.animateTo(targetValue = xTarget,
                animationSpec = tween(durationMillis = if (shouldAnimate) 500 else 0,
                        easing = LinearEasing))
    }
    val infiniteTransition = rememberInfiniteTransition()
    val radius by infiniteTransition.animateFloat(initialValue = 7f, targetValue = 15f,
            animationSpec = infiniteRepeatable(
                    animation = tween(500, easing = LinearEasing),
                    repeatMode = RepeatMode.Reverse
            ))

    val opacity by infiniteTransition.animateFloat(initialValue = 0.7f, targetValue = 1f,
            animationSpec = infiniteRepeatable(animation = tween(500, easing = LinearEasing),
                    repeatMode = RepeatMode.Reverse))

    Canvas(modifier = modifier.padding(8.dp)) {
        val path = Path()
        val xBounds = Pair(0f, xTarget)
        val yBounds = getBounds(yAxis)

        val scaleX = size.width / (xBounds.second - xBounds.first)
        val scaleY = size.height / (yBounds.second - yBounds.first)
        val yMove = yBounds.first * scaleY
        val interval = (0..min(yAxis.size - 1, x .value.toInt()))
        val last = interval.last()

        interval.forEach { value ->
            val xPoint = value * scaleX
            val yPoint = size.height - (yAxis[value] * scaleY) + yMove
            if (value == 0) {
                path.moveTo(0f, yPoint)
                return@forEach
            }
            path.lineTo(xPoint, yPoint)
        }

        drawPath(path = path, brush = Brush.linearGradient(lineColors),
                style = Stroke(width = lineWidth))
        if (shouldDrawLiveDot) {
            drawCircle(lineColors.first(), radius,
                    Offset(last * scaleX, size.height - (yAxis[last] * scaleY) + yMove), opacity)
        }
    }
}

fun getBounds(list: List<Float>): Pair<Float, Float> {
    var min = Float.MAX_VALUE
    var max = -Float.MAX_VALUE

    list.forEach {
        // returns the smaller value between the receiver float and the provided float value.
        min = min.coerceAtMost(it)
        // returns the smaller value between the receiver float and the provided float value.
        max = max.coerceAtLeast(it)
    }
    return Pair(min, max)
}

@Composable
fun BarChart(modifier: Modifier = Modifier,
             barColors: List<Color> = listOf(MaterialTheme.colors.primary,
                     MaterialTheme.colors.primary),
             barWidth: Float = 25f,
             yAxisValue: List<Float>,
             shouldAnimate: Boolean = true) {
    val x = remember {
        Animatable(0f)
    }
    val yValues = remember {
        yAxisValue
    }
    val xTarget = (yValues.size - 1).toFloat()
    LaunchedEffect(Unit) {
        x.animateTo(targetValue = xTarget,
                animationSpec = tween(durationMillis = if (shouldAnimate) 500 else 0,
                        easing = LinearEasing))
    }
    Canvas(modifier = modifier.padding(horizontal = 8.dp)) {
        val xBounds = Pair(0f, xTarget)
        val yBounds = getBounds(yValues)

        val scaleX = size.width / (xBounds.second - xBounds.first)
        val scaleY = size.height / (yBounds.second - yBounds.first)

        val yMove = yBounds.first * scaleY
        (0..min(yValues.size - 1, x.value.toInt())).forEach { value ->
            val xOffset = value * scaleX
            val yOffset = size.height - (yValues[value] * scaleY) + yMove
            drawBar(topLeft = Offset(xOffset, yOffset), width = barWidth,
                    height = size.height - yOffset,
                    colors = barColors)
        }
    }
}

fun DrawScope.drawBar(topLeft: Offset, width: Float, height: Float, colors: List<Color>) {
    drawRect(topLeft = topLeft, brush = Brush.linearGradient(colors),
            size = Size(width, height))
}