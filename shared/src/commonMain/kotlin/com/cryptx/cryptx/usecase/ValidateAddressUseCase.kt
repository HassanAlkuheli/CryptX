package com.cryptx.cryptx.usecase

class ValidateAddressUseCase {

    fun execute(address: String): Boolean {
        return address.startsWith("0x") && address.length >= 10
    }
}