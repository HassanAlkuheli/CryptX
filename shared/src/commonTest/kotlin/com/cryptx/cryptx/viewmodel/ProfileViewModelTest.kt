package com.cryptx.cryptx.viewmodel

import com.cryptx.cryptx.repository.ProfileRepositoryImpl
import com.cryptx.cryptx.usecase.GetProfileUseCase
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class ProfileViewModelTest {

    @Test
    fun loadProfile_populatesState() = runTest {
        val dispatcher = UnconfinedTestDispatcher(testScheduler)
        val usecase = GetProfileUseCase(ProfileRepositoryImpl())
        val vm = ProfileViewModel(usecase, dispatcher)

        vm.loadProfile()
        testScheduler.advanceUntilIdle()

        val state = vm.state.value
        assertNotNull(state.profile)
        assertEquals("Hassan Alkuheli", state.profile?.name)
    }
}
