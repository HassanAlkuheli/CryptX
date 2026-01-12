package com.cryptx.cryptx.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cryptx.cryptx.ui.theme.*
import java.text.NumberFormat
import java.util.Locale
import kotlin.random.Random

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
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp)
        ) {
            // Chart positioned absolutely in the center-right area
            MiniChart(
                isPositive = asset.change >= 0,
                seed = asset.symbol.hashCode(),
                modifier = Modifier
                    .width(80.dp)
                    .height(40.dp)
                    .align(Alignment.CenterEnd)
                    .offset(x = (-80).dp) // Offset from the right edge
            )

            Row(
                modifier = Modifier
                    .fillMaxSize()
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

                // Spacer for chart area
                Spacer(modifier = Modifier.width(100.dp))

                Column(
                    horizontalAlignment = Alignment.End
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
    modifier: Modifier = Modifier,
    seed: Int = 0
) {
    val color = if (isPositive) Success else Error
    
    // Generate consistent random points based on seed
    val points = remember(seed, isPositive) {
        val random = Random(seed)
        val numPoints = 8
        val baseValues = List(numPoints) { random.nextFloat() * 0.6f + 0.2f }
        // Ensure trend direction matches isPositive
        if (isPositive) {
            baseValues.mapIndexed { index, value -> 
                value + (index.toFloat() / numPoints) * 0.3f 
            }
        } else {
            baseValues.mapIndexed { index, value -> 
                value - (index.toFloat() / numPoints) * 0.3f + 0.3f
            }
        }
    }
    
    Canvas(modifier = modifier) {
        val width = size.width
        val height = size.height
        val stepX = width / (points.size - 1)
        
        val path = Path().apply {
            points.forEachIndexed { index, value ->
                val x = index * stepX
                val y = height - (value.coerceIn(0f, 1f) * height * 0.8f) - height * 0.1f
                
                if (index == 0) {
                    moveTo(x, y)
                } else {
                    // Use quadratic bezier for smooth curves
                    val prevX = (index - 1) * stepX
                    val prevY = height - (points[index - 1].coerceIn(0f, 1f) * height * 0.8f) - height * 0.1f
                    val controlX = (prevX + x) / 2
                    quadraticBezierTo(controlX, prevY, (prevX + x) / 2, (prevY + y) / 2)
                    quadraticBezierTo((prevX + x) / 2, (prevY + y) / 2, x, y)
                }
            }
        }
        
        drawPath(
            path = path,
            color = color,
            style = Stroke(
                width = 2.dp.toPx(),
                cap = StrokeCap.Round,
                join = StrokeJoin.Round
            )
        )
    }
}

private fun formatCurrency(amount: Double): String {
    val formatter = NumberFormat.getCurrencyInstance(Locale.US)
    return formatter.format(amount)
}
