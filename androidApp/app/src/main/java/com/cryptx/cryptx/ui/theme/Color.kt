package com.cryptx.cryptx.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

// Primary Colors - Deep blue/purple from design
val Primary = Color(0xFF6366F1)        // Indigo-500
val PrimaryVariant = Color(0xFF4F46E5) // Indigo-600 darker
val PrimaryLight = Color(0xFF818CF8)   // Indigo-400 lighter

// Secondary (accent / highlights)
val Secondary = Color(0xFFFFD93D)      // Gold/Yellow accent
val Accent = Color(0xFF00D9FF)         // Cyan accent for highlights
val AccentPink = Color(0xFFEC4899)     // Pink accent for glows
val AccentPurple = Color(0xFF8B5CF6)   // Purple accent

// Background - Deep dark navy from design
val Background = Color(0xFF0F0F1E)     // Very dark navy background
val Surface = Color(0xFF1A1A2E)        // Card surface - slightly lighter
val SurfaceVariant = Color(0xFF16213E) // Alternative surface
val SurfaceCard = Color(0xFF1E1E32)    // Card background

// Glow colors for background effects
val GlowPurple = Color(0xFF7C3AED)
val GlowBlue = Color(0xFF3B82F6)
val GlowPink = Color(0xFFEC4899)

// Text Colors
val OnPrimary = Color.White
val OnBackground = Color.White
val OnSurface = Color.White
val TextPrimary = Color.White          // Primary text color
val TextSecondary = Color(0xFFB0B0B0)  // Muted text
val TextMuted = Color(0xFF6B7280)      // More muted

// Status Colors
val Error = Color(0xFFEF4444)          // Red for errors
val Success = Color(0xFF10B981)        // Green for success/positive
val Warning = Color(0xFFF59E0B)        // Orange warning

// Balance Card - Dark gradient matching design
val BalanceCardBackground = Color(0xFF1A1A2E)
val BalanceCardGradient = Brush.verticalGradient(
    colors = listOf(
        Color(0xFF1E1E32),
        Color(0xFF16162A)
    )
)

// Crypto card gradients
val BitcoinCardGradient = Brush.linearGradient(
    colors = listOf(Color(0xFF1E1E32), Color(0xFF2A1E3E))
)
val EthereumCardGradient = Brush.linearGradient(
    colors = listOf(Color(0xFF1E2632), Color(0xFF1E1E32))
)

// Nav bar
val NavBackground = Color(0xFF0F0F1E)
val NavItemSelected = Primary
val NavItemUnselected = Color(0xFF6B7280)