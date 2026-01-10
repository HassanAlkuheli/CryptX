package com.cryptx.cryptx.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cryptx.cryptx.view.WalletViewModel
import com.cryptx.cryptx.ui.theme.*

data class Asset(
    val symbol: String,
    val name: String,
    val quantity: Double,
    val value: Double,
    val change: Double,
    val changeColor: Color
)

@Composable
fun DashboardScreen(viewModel: WalletViewModel, onProfileClick: () -> Unit) {
    val walletState by viewModel.walletState.collectAsState()

    // Mock data for holdings
    val assets = listOf(
        Asset("ETH", "Ethereum", 2.5, 503.12, 8.2, Color.Green),
        Asset("BTC", "Bitcoin", 0.08, 26927.0, -2.1, Color.Red),
        Asset("LTC", "Litecoin", 5.0, 6927.0, 5.3, Color.Green),
        Asset("XRP", "Ripple", 1000.0, 4637.0, -1.5, Color.Red)
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .padding(16.dp)
    ) {
        // Header
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Hello Alex",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = OnBackground
                    )
                }

                IconButton(onClick = onProfileClick) {
                    Icon(
                        imageVector = Icons.Filled.Settings,
                        contentDescription = "Settings",
                        tint = OnBackground
                    )
                }
            }
        }

        // Balance Card
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                colors = CardDefaults.cardColors(containerColor = Surface),
                shape = MaterialTheme.shapes.large
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        text = "Current Balance",
                        fontSize = 12.sp,
                        color = OnSurface.copy(alpha = 0.7f),
                        fontWeight = FontWeight.Medium
                    )

                    Row(
                        modifier = Modifier.padding(top = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "$87,430.12",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            color = OnSurface
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = "+10.2%",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Green
                        )
                    }
                }
            }
        }

        // Deposit / Withdraw Buttons
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = { },
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Primary),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text("Deposit", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                }

                OutlinedButton(
                    onClick = { },
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    colors = OutlinedButtonDefaults.outlinedButtonColors(
                        contentColor = OnBackground
                    ),
                    border = ButtonDefaults.outlinedButtonBorder,
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text("Withdraw", fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                }
            }
        }

        // Holdings Header
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Holdings",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = OnBackground
                )

                TextButton(onClick = { }) {
                    Text("See All", color = Primary, fontSize = 12.sp)
                }
            }
        }

        // Assets List
        items(assets) { asset ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
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
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = asset.symbol,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = OnSurface
                        )

                        Text(
                            text = asset.name,
                            fontSize = 12.sp,
                            color = OnSurface.copy(alpha = 0.6f)
                        )
                    }

                    Column(horizontalAlignment = Alignment.End) {
                        Text(
                            text = "$${asset.value}",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = OnSurface
                        )

                        Text(
                            text = "${asset.quantity} ${asset.symbol}",
                            fontSize = 12.sp,
                            color = OnSurface.copy(alpha = 0.6f)
                        )
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
