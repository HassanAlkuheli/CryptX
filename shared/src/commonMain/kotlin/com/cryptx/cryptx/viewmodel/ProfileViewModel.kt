package com.cryptx.cryptx.viewmodel

import com.cryptx.cryptx.state.ProfileState
import com.cryptx.cryptx.usecase.GetProfileUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val getProfileUseCase: GetProfileUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) {

    private val _state = MutableStateFlow(ProfileState())
    val state: StateFlow<ProfileState> = _state

    private val scope = CoroutineScope(dispatcher)

    fun loadProfile() {
        _state.value = _state.value.copy(isLoading = true, error = null)
        scope.launch {
            try {
                val profile = getProfileUseCase.execute()
                _state.value = _state.value.copy(profile = profile, isLoading = false)
            } catch (e: Exception) {
                _state.value = _state.value.copy(isLoading = false, error = e.message)
            }
        }
    }
}
