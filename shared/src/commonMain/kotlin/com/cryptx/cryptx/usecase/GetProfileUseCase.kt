package com.cryptx.cryptx.usecase

import com.cryptx.cryptx.domain.Profile
import com.cryptx.cryptx.repository.ProfileRepository

class GetProfileUseCase(
    private val profileRepository: ProfileRepository
) {
    suspend fun execute(): Profile = profileRepository.getProfile()
}
