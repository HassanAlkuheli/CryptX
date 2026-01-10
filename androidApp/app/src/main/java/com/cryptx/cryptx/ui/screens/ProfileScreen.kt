package com.cryptx.cryptx.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Copy
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cryptx.cryptx.ui.theme.*

@Composable
fun ProfileScreen(onBackClick: () -> Unit) {
    var isBiometricEnabled by remember { mutableStateOf(false) }
    var isDarkMode by remember { mutableStateOf(true) }
    val clipboardManager = LocalClipboardManager.current

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
                text = "Profile Settings",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = OnBackground,
                modifier = Modifier.weight(1f)
            )
        }

        // Wallet Address Section
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            colors = CardDefaults.cardColors(containerColor = Surface),
            shape = MaterialTheme.shapes.large
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Wallet Address",
                    fontSize = 12.sp,
                    color = OnSurface.copy(alpha = 0.7f),
                    fontWeight = FontWeight.Medium
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "1A2B3C4D5E6F7G8H9I0J",
                        fontSize = 14.sp,
                        color = OnSurface,
                        fontWeight = FontWeight.SemiBold
                    )

                    IconButton(
                        onClick = {
                            clipboardManager.setText(
                                AnnotatedString("1A2B3C4D5E6F7G8H9I0J1K2L3M4N5O6P")
                            )
                        },
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Copy,
                            contentDescription = "Copy",
                            tint = Primary,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }
        }

        // Security Section
        Text(
            text = "Security",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = OnBackground,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            colors = CardDefaults.cardColors(containerColor = Surface),
            shape = MaterialTheme.shapes.large
        ) {
            Column {
                // Biometric
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Biometric Authentication",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = OnSurface
                        )

                        Text(
                            text = "Face ID / Fingerprint",
                            fontSize = 12.sp,
                            color = OnSurface.copy(alpha = 0.6f)
                        )
                    }

                    Switch(
                        checked = isBiometricEnabled,
                        onCheckedChange = { isBiometricEnabled = it },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Primary,
                            uncheckedThumbColor = OnSurface.copy(alpha = 0.3f)
                        )
                    )
                }

                Divider(
                    color = OnSurface.copy(alpha = 0.1f),
                    thickness = 1.dp,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                // Theme Mode
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Dark Mode",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = OnSurface
                        )

                        Text(
                            text = "Theme preference",
                            fontSize = 12.sp,
                            color = OnSurface.copy(alpha = 0.6f)
                        )
                    }

                    Switch(
                        checked = isDarkMode,
                        onCheckedChange = { isDarkMode = it },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Primary,
                            uncheckedThumbColor = OnSurface.copy(alpha = 0.3f)
                        )
                    )
                }
            }
        }

        // General Section
        Text(
            text = "General",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = OnBackground,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            colors = CardDefaults.cardColors(containerColor = Surface),
            shape = MaterialTheme.shapes.large
        ) {
            Column {
                TextButton(
                    onClick = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    colors = ButtonDefaults.textButtonColors(contentColor = OnSurface)
                ) {
                    Text(
                        text = "App Version: 1.0.0",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Divider(
                    color = OnSurface.copy(alpha = 0.1f),
                    thickness = 1.dp,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                TextButton(
                    onClick = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    colors = ButtonDefaults.textButtonColors(contentColor = androidx.compose.ui.graphics.Color.Red)
                ) {
                    Text(
                        text = "Reset Wallet",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Divider(
                    color = OnSurface.copy(alpha = 0.1f),
                    thickness = 1.dp,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                TextButton(
                    onClick = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    colors = ButtonDefaults.textButtonColors(contentColor = androidx.compose.ui.graphics.Color.Red)
                ) {
                    Text(
                        text = "Logout",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}
