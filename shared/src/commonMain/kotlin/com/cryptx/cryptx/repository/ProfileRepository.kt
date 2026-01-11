package com.cryptx.cryptx.repository

import com.cryptx.cryptx.domain.Profile

/** Repository for Profile - no business logic, just pull and return */
interface ProfileRepository {
    suspend fun getProfile(): Profile
}

class ProfileRepositoryImpl : ProfileRepository {
    override suspend fun getProfile(): Profile = ProfileDataSource.getProfile()
}
