package com.fahlabs.mafia.security

import com.fahlabs.mafia.domain.model.Role

data class PrivatePlayerView(
    val playerId: Int,
    val playerName: String,
    val role: Role
)