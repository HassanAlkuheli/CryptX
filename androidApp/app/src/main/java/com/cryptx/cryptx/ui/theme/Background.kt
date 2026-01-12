package com.cryptx.cryptx.ui.theme

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cryptx.cryptx.R

/**
 * A composable that provides a dynamic background with glowing orbs and patterns.
 * Creates a visually rich dark theme with subtle lighting effects.
 */
@Composable
fun PatternBackground(
    modifier: Modifier = Modifier,
    showRandomLines: Boolean = true,
    showGlowEffects: Boolean = true,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF0F0F1E),
                        Color(0xFF0A0A15),
                        Color(0xFF0F0F1E)
                    )
                )
            )
    ) {
        // Glowing orb effects to break the darkness
        if (showGlowEffects) {
            // Purple glow - top right
            GlowOrb(
                color = GlowPurple.copy(alpha = 0.15f),
                modifier = Modifier
                    .size(300.dp)
                    .offset(x = 150.dp, y = (-50).dp)
                    .blur(100.dp)
                    .align(Alignment.TopEnd)
            )
            
            // Blue glow - center left
            GlowOrb(
                color = GlowBlue.copy(alpha = 0.12f),
                modifier = Modifier
                    .size(250.dp)
                    .offset(x = (-100).dp, y = 200.dp)
                    .blur(80.dp)
                    .align(Alignment.CenterStart)
            )
            
            // Pink glow - bottom center
            GlowOrb(
                color = GlowPink.copy(alpha = 0.08f),
                modifier = Modifier
                    .size(200.dp)
                    .offset(y = 100.dp)
                    .blur(60.dp)
                    .align(Alignment.BottomCenter)
            )
        }

        // Random direction lines pattern overlay
        if (showRandomLines) {
            Image(
                painter = painterResource(id = R.drawable.linegoesrandomdirection),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(0.08f)
            )
        }

        // Actual content
        content()
    }
}

@Composable
fun GlowOrb(
    color: Color,
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier) {
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(
                    color,
                    color.copy(alpha = color.alpha * 0.5f),
                    Color.Transparent
                ),
                center = center,
                radius = size.minDimension / 2
            )
        )
    }
}
