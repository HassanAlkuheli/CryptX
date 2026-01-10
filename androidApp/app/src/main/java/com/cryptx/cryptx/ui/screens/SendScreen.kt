package com.cryptx.cryptx.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cryptx.cryptx.view.SendTransactionViewModel
import com.cryptx.cryptx.ui.theme.*
import com.cryptx.cryptx.repository.FakeNetworkRepository
import com.cryptx.cryptx.repository.FakeWalletRepository
import com.cryptx.cryptx.usecase.SendTransactionUseCase
import com.cryptx.cryptx.usecase.ValidateAddressUseCase

@Composable
fun SendScreen(viewModel: SendTransactionViewModel, onBackClick: () -> Unit) {
    val sendState by viewModel.sendState.collectAsState()
    var toAddress by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .padding(16.dp)
    ) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = OnBackground
                )
            }

            Text(
                text = "Send Transaction",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = OnBackground,
                modifier = Modifier.weight(1f)
            )
        }

        // From Address
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
                    color = OnSurface.copy(alpha = 0.7f),
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
            label = { Text("To Address", color = OnBackground) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Primary,
                unfocusedBorderColor = OnBackground.copy(alpha = 0.3f),
                focusedTextColor = OnBackground,
                unfocusedTextColor = OnBackground
            ),
            shape = MaterialTheme.shapes.medium
        )

        // Amount Input
        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Amount (USD)", color = OnBackground) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Primary,
                unfocusedBorderColor = OnBackground.copy(alpha = 0.3f),
                focusedTextColor = OnBackground,
                unfocusedTextColor = OnBackground
            ),
            shape = MaterialTheme.shapes.medium
        )

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
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Available Balance",
                        fontSize = 12.sp,
                        color = OnSurface.copy(alpha = 0.7f)
                    )

                    Text(
                        text = "$87,430.12",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = OnSurface,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }

                if (sendState.isNetworkAvailable) {
                    Text(
                        text = "✓ Network OK",
                        fontSize = 12.sp,
                        color = androidx.compose.ui.graphics.Color.Green,
                        fontWeight = FontWeight.SemiBold
                    )
                } else {
                    Text(
                        text = "✗ No Network",
                        fontSize = 12.sp,
                        color = Error,
                        fontWeight = FontWeight.SemiBold
                    )
                }
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

        Spacer(modifier = Modifier.weight(1f))

        // Send Button
        Button(
            onClick = {
                viewModel.send(toAddress, amount.toDoubleOrNull() ?: 0.0)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (sendState.isLoading) Primary.copy(alpha = 0.5f) else Primary
            ),
            enabled = !sendState.isLoading && toAddress.isNotEmpty() && amount.isNotEmpty(),
            shape = MaterialTheme.shapes.medium
        ) {
            if (sendState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    color = OnBackground,
                    strokeWidth = 2.dp
                )
            } else {
                Text(
                    text = "Send",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = OnBackground
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun SendScreenPreview() {
    CryptoWalletTheme {
        val sendViewModel = SendTransactionViewModel(
            SendTransactionUseCase(
                FakeWalletRepository(),
                FakeNetworkRepository(),
                ValidateAddressUseCase()
            ),
            ValidateAddressUseCase()
        )
        SendScreen(
            viewModel = sendViewModel,
            onBackClick = {}
        )
    }
}
