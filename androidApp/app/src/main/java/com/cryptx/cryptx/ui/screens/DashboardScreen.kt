package com.cryptx.cryptx.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cryptx.cryptx.ui.components.*
import com.cryptx.cryptx.ui.theme.*
import com.cryptx.cryptx.viewmodel.WalletViewModel

@Composable
fun DashboardScreen(
    viewModel: WalletViewModel,
    onProfileClick: () -> Unit,
    onSendClick: () -> Unit
) {
    val walletState by viewModel.state.collectAsState()
    
    // Sort state: true = highest first, false = lowest first
    var sortHighestFirst by remember { mutableStateOf(true) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Main content
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentPadding = PaddingValues(bottom = 80.dp)
            ) {
                // Header
                item {
                    DashboardHeader(
                        userName = "Alex",
                        onSettingsClick = onProfileClick
                    )
                }

                // Calculate total balance from holdings
                val totalBalance = walletState.holdings.sumOf { it.eqToUsdt }

                // Balance Card with transaction button
                item {
                    BalanceCard(
                        balance = if (totalBalance > 0) totalBalance else 87430.12,
                        percentageChange = 10.2,
                        onTransactionClick = onSendClick,
                        modifier = Modifier.padding(horizontal = 20.dp)
                    )
                }

                // Spacing after balance card
                item {
                    Spacer(modifier = Modifier.height(24.dp))
                }

                // Holdings Header with Sort Button
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
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

                        // Sort toggle button
                        TextButton(
                            onClick = { sortHighestFirst = !sortHighestFirst }
                        ) {
                            Icon(
                                imageVector = if (sortHighestFirst) 
                                    Icons.Filled.KeyboardArrowDown 
                                else 
                                    Icons.Filled.KeyboardArrowUp,
                                contentDescription = if (sortHighestFirst) "Highest first" else "Lowest first",
                                tint = Primary,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = if (sortHighestFirst) "Highest" else "Lowest",
                                color = Primary,
                                fontSize = 12.sp
                            )
                        }
                    }
                }

                // Holdings List from shared WalletState
                val assets = if (walletState.holdings.isNotEmpty()) {
                    walletState.holdings.map { holding ->
                        AssetItemData(
                            symbol = holding.symbol,
                            name = holding.name,
                            quantity = holding.amount,
                            value = holding.eqToUsdt,
                            change = when (holding.symbol) {
                                "ETH" -> 8.2
                                "BTC" -> -2.1
                                "LTC" -> 5.3
                                "XRP" -> -1.5
                                else -> 0.0
                            }
                        )
                    }
                } else {
                    listOf(
                        AssetItemData("ETH", "Ethereum", 50.0, 503.12, 8.2),
                        AssetItemData("BTC", "Bitcoin", 2.05, 26927.0, -2.1),
                        AssetItemData("LTC", "Litecoin", 2.05, 6927.0, 5.3),
                        AssetItemData("XRP", "Ripple", 2.05, 4637.0, -1.5)
                    )
                }
                
                // Sort assets based on current sort state
                val sortedAssets = if (sortHighestFirst) {
                    assets.sortedByDescending { it.value }
                } else {
                    assets.sortedBy { it.value }
                }

                items(sortedAssets) { asset ->
                    AssetItem(
                        asset = asset,
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 6.dp)
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}
