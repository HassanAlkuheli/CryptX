package com.cryptx.cryptx.usecase

/** Simple address validator used by send transaction flows */
class ValidateAddressUseCase {
    fun execute(address: String): Boolean {
        // Very simple validation for demo purposes
        return address.startsWith("0x") && address.length >= 10
    }
}
