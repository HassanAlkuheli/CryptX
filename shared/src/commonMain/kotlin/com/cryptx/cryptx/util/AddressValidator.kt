package com.cryptx.cryptx.util

class AddressValidator {
    fun isValid(address: String): Boolean {
        // Basic validation logic (placeholder)
        return address.isNotBlank() && address.length > 10
    }
}