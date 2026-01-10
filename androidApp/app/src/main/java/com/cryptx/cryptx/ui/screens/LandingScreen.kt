package com.cryptx.cryptx.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cryptx.cryptx.ui.theme.Background
import com.cryptx.cryptx.ui.theme.CryptoWalletTheme
import com.cryptx.cryptx.ui.theme.Primary
import com.cryptx.cryptx.ui.theme.OnBackground

@Composable
fun LandingScreen(onGetStarted: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        // Logo & Brand
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "CryptX",
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                color = OnBackground,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(
                text = "Jump start your crypto portfolio",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = OnBackground,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            Text(
                text = "Take your investment portfolio to next level",
                fontSize = 14.sp,
                color = OnBackground.copy(alpha = 0.7f),
                modifier = Modifier.padding(bottom = 32.dp)
            )
        }

        // CTA Button
        Button(
            onClick = onGetStarted,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Primary),
            shape = MaterialTheme.shapes.medium
        ) {
            Text(
                text = "Get Started",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = OnBackground
            )
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun LandingScreenPreview() {
    CryptoWalletTheme {
        LandingScreen(onGetStarted = {})
    }
}
