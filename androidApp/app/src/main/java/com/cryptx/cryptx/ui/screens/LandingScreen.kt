package com.cryptx.cryptx.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cryptx.cryptx.ui.components.PrimaryButton
import com.cryptx.cryptx.ui.theme.*

@Composable
fun LandingScreen(onGetStarted: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            // Logo
            Text(
                text = "CryptX",
                style = MaterialTheme.typography.displayLarge,
                color = OnBackground,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.weight(0.3f))

            // Hero Text
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Jump start your",
                    style = MaterialTheme.typography.headlineLarge,
                    color = OnBackground,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "crypto portfolio",
                    style = MaterialTheme.typography.headlineLarge,
                    color = Primary,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Take your investment portfolio to the next level",
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextSecondary,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.weight(0.5f))

            // CTA Button
            PrimaryButton(
                text = "Get Started",
                onClick = onGetStarted,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

        }
    }
}
