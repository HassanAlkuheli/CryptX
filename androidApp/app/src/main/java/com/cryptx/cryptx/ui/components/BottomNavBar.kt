package com.cryptx.cryptx.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.cryptx.cryptx.ui.theme.*

enum class NavItem {
    HOME, CHARTS, TRANSACTION, SETTINGS, PROFILE
}

@Composable
fun BottomNavBar(
    selectedItem: NavItem,
    onItemSelected: (NavItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        NavBackground.copy(alpha = 0.98f),
                        NavBackground
                    )
                )
            )
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Wallet / Home
            NavBarItem(
                icon = if (selectedItem == NavItem.HOME) 
                    Icons.Filled.Home 
                else 
                    Icons.Outlined.Home,
                isSelected = selectedItem == NavItem.HOME,
                onClick = { onItemSelected(NavItem.HOME) }
            )

            // Charts / Market (using Star as placeholder)
            NavBarItem(
                icon = if (selectedItem == NavItem.CHARTS) 
                    Icons.Filled.Star 
                else 
                    Icons.Outlined.Star,
                isSelected = selectedItem == NavItem.CHARTS,
                onClick = { onItemSelected(NavItem.CHARTS) }
            )

            // Transaction (center - prominent with gradient circle)
            TransactionNavItem(
                isSelected = selectedItem == NavItem.TRANSACTION,
                onClick = { onItemSelected(NavItem.TRANSACTION) }
            )

            // Settings
            NavBarItem(
                icon = if (selectedItem == NavItem.SETTINGS) 
                    Icons.Filled.Settings 
                else 
                    Icons.Outlined.Settings,
                isSelected = selectedItem == NavItem.SETTINGS,
                onClick = { onItemSelected(NavItem.SETTINGS) }
            )

            // Profile
            NavBarItem(
                icon = if (selectedItem == NavItem.PROFILE) 
                    Icons.Filled.Person 
                else 
                    Icons.Outlined.Person,
                isSelected = selectedItem == NavItem.PROFILE,
                onClick = { onItemSelected(NavItem.PROFILE) }
            )
        }
    }
}

@Composable
private fun NavBarItem(
    icon: ImageVector,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = if (isSelected) Primary else NavItemUnselected,
            modifier = Modifier.size(26.dp)
        )
    }
}

@Composable
private fun TransactionNavItem(
    isSelected: Boolean,
    onClick: () -> Unit
) {
    // Prominent center button with gradient background
    Box(
        modifier = Modifier
            .size(56.dp)
            .clip(CircleShape)
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        AccentPurple,
                        AccentPink
                    )
                )
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Filled.Refresh,
            contentDescription = "Transaction",
            tint = Color.White,
            modifier = Modifier.size(28.dp)
        )
    }
}
