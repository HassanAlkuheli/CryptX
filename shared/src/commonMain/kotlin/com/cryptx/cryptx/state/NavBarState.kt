package com.cryptx.cryptx.state

/** Screens represented in the bottom navigation */
enum class NavScreen {
    HOME,
    CHARTS,
    TRANSACTION,
    SETTINGS,
    PROFILE
}

/** State holder for bottom navigation selection */
data class NavBarState(
    val selected: NavScreen = NavScreen.HOME
)
