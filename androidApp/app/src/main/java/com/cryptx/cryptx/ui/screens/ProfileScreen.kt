package com.cryptx.cryptx.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cryptx.cryptx.ui.components.ScreenHeader
import com.cryptx.cryptx.ui.theme.*

@Composable
fun ProfileScreen(onBackClick: () -> Unit) {
    var isBiometricEnabled by remember { mutableStateOf(false) }
    var isDarkMode by remember { mutableStateOf(true) }
    var notificationsEnabled by remember { mutableStateOf(true) }
    val clipboardManager = LocalClipboardManager.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Top bar removed (was ScreenHeader)
            Spacer(modifier = Modifier.height(8.dp))

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 20.dp)
            ) {
                // Wallet Address Section
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp),
                    colors = CardDefaults.cardColors(containerColor = Surface),
                    shape = MaterialTheme.shapes.large
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "Wallet Address",
                            fontSize = 12.sp,
                            color = TextSecondary,
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

                            TextButton(
                                onClick = {
                                    clipboardManager.setText(
                                        AnnotatedString("1A2B3C4D5E6F7G8H9I0J1K2L3M4N5O6P")
                                    )
                                }
                            ) {
                                Text(
                                    text = "Copy",
                                    color = Primary,
                                    fontSize = 12.sp
                                )
                            }
                        }
                    }
                }

                // Security Section
                SectionTitle(text = "Security")

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp),
                    colors = CardDefaults.cardColors(containerColor = Surface),
                    shape = MaterialTheme.shapes.large
                ) {
                    Column {
                        // Biometric
                        SettingsToggleItem(
                            title = "Biometric Authentication",
                            subtitle = "Face ID / Fingerprint",
                            isChecked = isBiometricEnabled,
                            onCheckedChange = { isBiometricEnabled = it }
                        )

                        Divider(
                            color = OnSurface.copy(alpha = 0.1f),
                            thickness = 1.dp,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )

                        // Dark Mode
                        SettingsToggleItem(
                            title = "Dark Mode",
                            subtitle = "Theme preference",
                            isChecked = isDarkMode,
                            onCheckedChange = { isDarkMode = it }
                        )
                    }
                }

                // Notifications Section
                SectionTitle(text = "Notifications")

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp),
                    colors = CardDefaults.cardColors(containerColor = Surface),
                    shape = MaterialTheme.shapes.large
                ) {
                    SettingsToggleItem(
                        title = "Push Notifications",
                        subtitle = "Transaction alerts",
                        isChecked = notificationsEnabled,
                        onCheckedChange = { notificationsEnabled = it }
                    )
                }

                // General Section
                SectionTitle(text = "General")

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp),
                    colors = CardDefaults.cardColors(containerColor = Surface),
                    shape = MaterialTheme.shapes.large
                ) {
                    Column {
                        SettingsNavigationItem(
                            title = "Transaction History",
                            onClick = { /* TODO */ }
                        )

                        Divider(
                            color = OnSurface.copy(alpha = 0.1f),
                            thickness = 1.dp,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )

                        SettingsNavigationItem(
                            title = "Currency Preferences",
                            onClick = { /* TODO */ }
                        )

                        Divider(
                            color = OnSurface.copy(alpha = 0.1f),
                            thickness = 1.dp,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )

                        SettingsNavigationItem(
                            title = "Help & Support",
                            onClick = { /* TODO */ }
                        )

                        Divider(
                            color = OnSurface.copy(alpha = 0.1f),
                            thickness = 1.dp,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )

                        SettingsNavigationItem(
                            title = "About",
                            onClick = { /* TODO */ }
                        )
                    }
                }

                // Sign Out
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 32.dp)
                        .clickable { /* TODO */ },
                    colors = CardDefaults.cardColors(containerColor = Error.copy(alpha = 0.1f)),
                    shape = MaterialTheme.shapes.large
                ) {
                    Text(
                        text = "Sign Out",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Error,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
private fun SectionTitle(text: String) {
    Text(
        text = text,
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        color = OnBackground,
        modifier = Modifier.padding(bottom = 12.dp)
    )
}

@Composable
private fun SettingsToggleItem(
    title: String,
    subtitle: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
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
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = OnSurface
            )
            Text(
                text = subtitle,
                fontSize = 12.sp,
                color = TextSecondary
            )
        }

        Switch(
            checked = isChecked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = OnPrimary,
                checkedTrackColor = Primary,
                uncheckedThumbColor = OnSurface.copy(alpha = 0.3f),
                uncheckedTrackColor = Surface
            )
        )
    }
}

@Composable
private fun SettingsNavigationItem(
    title: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = OnSurface
        )

        Icon(
            imageVector = Icons.Filled.KeyboardArrowRight,
            contentDescription = null,
            tint = TextSecondary
        )
    }
}
