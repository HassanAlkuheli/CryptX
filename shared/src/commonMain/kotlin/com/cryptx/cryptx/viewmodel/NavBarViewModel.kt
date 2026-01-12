package com.cryptx.cryptx.viewmodel

import com.cryptx.cryptx.state.NavBarState
import com.cryptx.cryptx.state.NavScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/** Simple ViewModel for Bottom Navigation selection */
class NavBarViewModel {
    private val _state = MutableStateFlow(NavBarState())
    val state: StateFlow<NavBarState> = _state

    fun select(screen: NavScreen) {
        _state.value = _state.value.copy(selected = screen)
    }
}
