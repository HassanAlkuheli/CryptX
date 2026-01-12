package com.cryptx.cryptx.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cryptx.cryptx.ui.theme.*

enum class NavItem {
    HOME, TRANSACTION, PROFILE
}

@Composable
fun BottomNavBar(
    selectedItem: NavItem,
    onItemSelected: (NavItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(NavBackground)
            .padding(horizontal = 48.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Home / Wallet
        NavBarItem(
            icon = if (selectedItem == NavItem.HOME) Icons.Filled.Home else Icons.Outlined.Home,
            isSelected = selectedItem == NavItem.HOME,
            onClick = { onItemSelected(NavItem.HOME) }
        )

        // Transaction (center - always has circle, highlighted when selected)
        TransactionNavItem(
            isSelected = selectedItem == NavItem.TRANSACTION,
            onClick = { onItemSelected(NavItem.TRANSACTION) }
        )

        // Profile
        NavBarItem(
            icon = if (selectedItem == NavItem.PROFILE) Icons.Filled.Person else Icons.Outlined.Person,
            isSelected = selectedItem == NavItem.PROFILE,
            onClick = { onItemSelected(NavItem.PROFILE) }
        )
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
            tint = if (isSelected) OnBackground else NavItemUnselected,
            modifier = Modifier.size(28.dp)
        )
    }
}

@Composable
private fun TransactionNavItem(
    isSelected: Boolean,
    onClick: () -> Unit
) {
    // Always show circle, change color based on selection
    Box(
        modifier = Modifier
            .size(56.dp)
            .clip(CircleShape)
            .background(if (isSelected) Primary else Surface)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        // Swap/Exchange icon using two arrows
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Up arrow
            Icon(
                imageVector = Icons.Filled.Home, // Will be replaced with swap arrows below
                contentDescription = "Transaction",
                tint = if (isSelected) OnBackground else NavItemUnselected,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}
