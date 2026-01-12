package com.cryptx.cryptx.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.cryptx.cryptx.R

/**
 * A composable that provides a background with decorative line patterns.
 * Uses linegoesrandomdirection.png as pattern overlay.
 */
@Composable
fun PatternBackground(
    modifier: Modifier = Modifier,
    showRandomLines: Boolean = true,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Background)
    ) {
        // Random direction lines pattern overlay
        if (showRandomLines) {
            Image(
                painter = painterResource(id = R.drawable.linegoesrandomdirection),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(0.12f)
            )
        }

        // Actual content
        content()
    }
}
