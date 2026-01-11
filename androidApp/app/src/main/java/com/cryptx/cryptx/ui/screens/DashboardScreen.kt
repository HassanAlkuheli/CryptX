package com.cryptx.cryptx.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cryptx.cryptx.ui.components.*
import com.cryptx.cryptx.ui.theme.*
import com.cryptx.cryptx.view.WalletViewModel

@Composable
fun DashboardScreen(
    viewModel: WalletViewModel,
    onProfileClick: () -> Unit,
    onSendClick: () -> Unit
) {
    val walletState by viewModel.walletState.collectAsState()

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

                // Balance Card
                item {
                    BalanceCard(
                        balance = walletState.wallet?.totalBalance ?: 87430.12,
                        percentageChange = 10.2,
                        modifier = Modifier.padding(horizontal = 20.dp)
                    )
                }

                // Action Buttons
                item {
                    ActionButtonRow(
                        onDepositClick = { /* TODO */ },
                        onWithdrawClick = onSendClick,
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 24.dp)
                    )
                }

                // Holdings Header
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

                        TextButton(onClick = { }) {
                            Text(
                                text = "See All",
                                color = Primary,
                                fontSize = 12.sp
                            )
                        }
                    }
                }

                // Holdings List
                val assets = walletState.wallet?.balances?.map { balance ->
                    AssetItemData(
                        symbol = balance.symbol,
                        name = balance.name,
                        quantity = balance.quantity,
                        value = balance.totalValue,
                        change = when (balance.symbol) {
                            "ETH" -> 8.2
                            "BTC" -> -2.1
                            "LTC" -> 5.3
                            "XRP" -> -1.5
                            else -> 0.0
                        }
                    )
                } ?: listOf(
                    AssetItemData("ETH", "Ethereum", 50.0, 503.12, 8.2),
                    AssetItemData("BTC", "Bitcoin", 2.05, 26927.0, -2.1),
                    AssetItemData("LTC", "Litecoin", 2.05, 6927.0, 5.3),
                    AssetItemData("XRP", "Ripple", 2.05, 4637.0, -1.5)
                )

                items(assets) { asset ->
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
