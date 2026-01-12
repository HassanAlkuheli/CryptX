package com.cryptx.cryptx.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cryptx.cryptx.ui.theme.*
import java.text.NumberFormat
import java.util.Locale

@Composable
fun BalanceCard(
    balance: Double,
    percentageChange: Double,
    modifier: Modifier = Modifier,
    onTransactionClick: (() -> Unit)? = null
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.large)
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        SurfaceCard,
                        SurfaceCard.copy(alpha = 0.95f)
                    )
                )
            )
    ) {
        // Subtle glow effect inside the card
        Canvas(
            modifier = Modifier
                .size(150.dp)
                .offset(x = (-30).dp, y = (-30).dp)
                .blur(50.dp)
                .align(Alignment.TopStart)
        ) {
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(
                        GlowPurple.copy(alpha = 0.15f),
                        Color.Transparent
                    )
                )
            )
        }

        Column(
            modifier = Modifier.padding(24.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = "Current Balance",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    color = TextSecondary
                )

                // Transaction button
                if (onTransactionClick != null) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(
                                Brush.linearGradient(
                                    colors = listOf(
                                        AccentPurple.copy(alpha = 0.3f),
                                        AccentPink.copy(alpha = 0.2f)
                                    )
                                )
                            )
                            .clickable { onTransactionClick() },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Send,
                            contentDescription = "Send Transaction",
                            tint = OnBackground,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Large balance display - focus element
            Text(
                text = formatCurrency(balance),
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = OnBackground,
                letterSpacing = (-1).sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Percentage change chip
            PercentageChip(
                percentage = percentageChange
            )
        }
    }
}

@Composable
fun PercentageChip(
    percentage: Double,
    modifier: Modifier = Modifier
) {
    val isPositive = percentage >= 0
    val backgroundColor = if (isPositive) Success.copy(alpha = 0.15f) else Error.copy(alpha = 0.15f)
    val textColor = if (isPositive) Success else Error
    val prefix = if (isPositive) "↑" else "↓"

    Box(
        modifier = modifier
            .clip(MaterialTheme.shapes.small)
            .background(backgroundColor)
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = "$prefix${String.format("%.1f", kotlin.math.abs(percentage))}%",
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = textColor
        )
    }
}

private fun formatCurrency(amount: Double): String {
    val formatter = NumberFormat.getCurrencyInstance(Locale.US)
    return formatter.format(amount)
}
