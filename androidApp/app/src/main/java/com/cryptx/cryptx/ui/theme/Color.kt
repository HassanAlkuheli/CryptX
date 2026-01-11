package com.cryptx.cryptx.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

// Primary Colors - Purple/Blue gradient style from design
val Primary = Color(0xFF6C5CE7)        // Purple primary
val PrimaryVariant = Color(0xFF5B4CD2) // Darker purple
val PrimaryLight = Color(0xFF8B7CF6)   // Lighter purple

// Secondary (accent / highlights)
val Secondary = Color(0xFFFFD93D)      // Gold/Yellow accent
val Accent = Color(0xFF00D9FF)         // Cyan accent for highlights

// Background - Deep dark from design
val Background = Color(0xFF0D0D0D)     // Very dark background
val Surface = Color(0xFF1A1A2E)        // Card surface - slightly lighter
val SurfaceVariant = Color(0xFF16213E) // Alternative surface

// Text Colors
val OnPrimary = Color.White
val OnBackground = Color.White
val OnSurface = Color.White
val TextSecondary = Color(0xFFB0B0B0)  // Muted text
val TextMuted = Color(0xFF6B7280)      // More muted

// Status Colors
val Error = Color(0xFFEF4444)          // Red for errors
val Success = Color(0xFF10B981)        // Green for success/positive
val Warning = Color(0xFFF59E0B)        // Orange warning

// Balance Card Gradient Colors (matching design)
val GradientStart = Color(0xFFE8D5B7)  // Light cream/beige
val GradientMiddle = Color(0xFFE9B8C9) // Light pink
val GradientEnd = Color(0xFFB4C7E7)    // Light blue

// Balance Card Gradient Brush
val BalanceCardGradient = Brush.horizontalGradient(
    colors = listOf(GradientStart, GradientMiddle, GradientEnd)
)

// Nav bar
val NavBackground = Color(0xFF0D0D0D)
val NavItemSelected = Primary
val NavItemUnselected = Color(0xFF6B7280)