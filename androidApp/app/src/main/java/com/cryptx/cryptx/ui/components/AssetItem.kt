package com.cryptx.cryptx.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cryptx.cryptx.ui.theme.*
import java.text.NumberFormat
import java.util.Locale

data class AssetItemData(
    val symbol: String,
    val name: String,
    val quantity: Double,
    val value: Double,
    val change: Double
)

@Composable
fun AssetItem(
    asset: AssetItemData,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Surface),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.weight(1f)
            ) {
                // Crypto Icon placeholder
                CryptoIcon(symbol = asset.symbol)

                Column {
                    Text(
                        text = asset.name,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = OnSurface
                    )
                    Text(
                        text = asset.symbol,
                        fontSize = 12.sp,
                        color = TextSecondary
                    )
                }
            }

            // Mini chart placeholder
            MiniChart(
                isPositive = asset.change >= 0,
                modifier = Modifier
                    .width(60.dp)
                    .height(30.dp)
            )

            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier.padding(start = 12.dp)
            ) {
                Text(
                    text = formatCurrency(asset.value),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = OnSurface
                )
                Text(
                    text = "${asset.quantity} ${asset.symbol}",
                    fontSize = 12.sp,
                    color = TextSecondary
                )
            }
        }
    }
}

@Composable
fun CryptoIcon(
    symbol: String,
    modifier: Modifier = Modifier
) {
    val backgroundColor = when (symbol) {
        "ETH" -> Color(0xFF627EEA)
        "BTC" -> Color(0xFFF7931A)
        "LTC" -> Color(0xFF345D9D)
        "XRP" -> Color(0xFF23292F)
        else -> Primary
    }

    Box(
        modifier = modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = symbol.take(1),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

@Composable
fun MiniChart(
    isPositive: Boolean,
    modifier: Modifier = Modifier
) {
    // Placeholder for mini chart - in real app would use Canvas or library
    val color = if (isPositive) Success else Error
    
    Box(
        modifier = modifier
            .background(color.copy(alpha = 0.1f), MaterialTheme.shapes.small)
    ) {
        // Simple line representation
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .align(Alignment.Center)
                .background(color.copy(alpha = 0.5f))
        )
    }
}

private fun formatCurrency(amount: Double): String {
    val formatter = NumberFormat.getCurrencyInstance(Locale.US)
    return formatter.format(amount)
}
