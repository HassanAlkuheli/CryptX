package com.cryptx.cryptx.repository

import com.cryptx.cryptx.domain.Profile

/** Mock datasource for Profile */
object ProfileDataSource {

    private val profile = Profile(
        name = "Hassan Alkuheli",
        imageUrl = "https://example.com/avatar.png"
    )

    fun getProfile(): Profile = profile

    fun setProfile(name: String, imageUrl: String?) : Profile {
        return Profile(name = name, imageUrl = imageUrl)
    }
}
