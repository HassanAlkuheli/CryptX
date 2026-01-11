package com.cryptx.cryptx.usecase

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ValidateAddressUseCaseTest {

    private val useCase = ValidateAddressUseCase()

    @Test
    fun `execute returns true for valid address starting with 0x and length >= 10`() {
        assertTrue(useCase.execute("0x1234567890"))
        assertTrue(useCase.execute("0xABCDEF123456789"))
    }

    @Test
    fun `execute returns false for invalid address not starting with 0x`() {
        assertFalse(useCase.execute("1A2B3C4D5E6F7G8H9I0J1K2L3M4N5O6P"))
    }

    @Test
    fun `execute returns false for address starting with 0x but length < 10`() {
        assertFalse(useCase.execute("0x123"))
    }

    @Test
    fun `execute returns false for empty address`() {
        assertFalse(useCase.execute(""))
    }
}