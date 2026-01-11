package com.cryptx.cryptx.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cryptx.cryptx.ui.components.PrimaryButton
import com.cryptx.cryptx.ui.components.ScreenHeader
import com.cryptx.cryptx.ui.theme.*
import com.cryptx.cryptx.viewmodel.SendTransactionViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionScreen(
    viewModel: SendTransactionViewModel,
    onBackClick: () -> Unit
) {
    val sendState by viewModel.state.collectAsState()
    var toAddress by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var selectedCrypto by remember { mutableStateOf("BTC") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Header
            ScreenHeader(
                title = "Send Transaction",
                onBackClick = onBackClick
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
            ) {
                // From Address Card
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    colors = CardDefaults.cardColors(containerColor = Surface),
                    shape = MaterialTheme.shapes.large
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "From Address",
                            fontSize = 12.sp,
                            color = TextSecondary,
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            text = "1A2B3C4D5E6F7G8H9I0J",
                            fontSize = 14.sp,
                            color = OnSurface,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }

                // To Address Input
                OutlinedTextField(
                    value = toAddress,
                    onValueChange = { toAddress = it },
                    label = { Text("To Address (starts with 0x)", color = TextSecondary) },
                    placeholder = { Text("0x...", color = TextMuted) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Primary,
                        unfocusedBorderColor = OnBackground.copy(alpha = 0.3f),
                        focusedTextColor = OnBackground,
                        unfocusedTextColor = OnBackground,
                        cursorColor = Primary
                    ),
                    shape = MaterialTheme.shapes.medium,
                    singleLine = true
                )

                // Amount Input
                OutlinedTextField(
                    value = amount,
                    onValueChange = { amount = it },
                    label = { Text("Amount", color = TextSecondary) },
                    placeholder = { Text("0.00", color = TextMuted) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Primary,
                        unfocusedBorderColor = OnBackground.copy(alpha = 0.3f),
                        focusedTextColor = OnBackground,
                        unfocusedTextColor = OnBackground,
                        cursorColor = Primary
                    ),
                    shape = MaterialTheme.shapes.medium,
                    singleLine = true
                )

                // Crypto Selection
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    colors = CardDefaults.cardColors(containerColor = Surface),
                    shape = MaterialTheme.shapes.large
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "Select Cryptocurrency",
                            fontSize = 12.sp,
                            color = TextSecondary,
                            fontWeight = FontWeight.Medium
                        )
                        
                        Spacer(modifier = Modifier.height(12.dp))
                        
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            listOf("BTC", "ETH", "LTC", "XRP").forEach { crypto ->
                                FilterChip(
                                    selected = selectedCrypto == crypto,
                                    onClick = { selectedCrypto = crypto },
                                    label = { Text(crypto) },
                                    colors = FilterChipDefaults.filterChipColors(
                                        selectedContainerColor = Primary,
                                        selectedLabelColor = OnPrimary
                                    )
                                )
                            }
                        }
                    }
                }

                // Balance Info
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp),
                    colors = CardDefaults.cardColors(containerColor = Surface),
                    shape = MaterialTheme.shapes.large
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "Available Balance",
                                fontSize = 12.sp,
                                color = TextSecondary
                            )
                            Text(
                                text = "$87,430.12",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = OnSurface,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }

                        // Network status is checked within use case; show general ready state
                        Text(
                            text = "Ready to Send",
                            fontSize = 12.sp,
                            color = Success,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                // Error Message
                if (sendState.error != null) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        colors = CardDefaults.cardColors(containerColor = Error.copy(alpha = 0.2f)),
                        shape = MaterialTheme.shapes.medium
                    ) {
                        Text(
                            text = sendState.error!!,
                            fontSize = 12.sp,
                            color = Error,
                            modifier = Modifier.padding(12.dp)
                        )
                    }
                }

                // Success Message
                if (sendState.transaction != null) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        colors = CardDefaults.cardColors(containerColor = Success.copy(alpha = 0.2f)),
                        shape = MaterialTheme.shapes.medium
                    ) {
                        Text(
                            text = "Transaction successful! ID: ${sendState.transaction!!.id}",
                            fontSize = 12.sp,
                            color = Success,
                            modifier = Modifier.padding(12.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                // Send Button
                PrimaryButton(
                    text = if (sendState.isLoading) "Sending..." else "Send",
                    onClick = {
                        viewModel.send(
                            toAddress,
                            amount.toDoubleOrNull() ?: 0.0,
                            selectedCrypto
                        )
                    },
                    enabled = !sendState.isLoading && toAddress.isNotEmpty() && amount.isNotEmpty(),
                    modifier = Modifier.padding(bottom = 24.dp)
                )
            }
        }
    }
}
