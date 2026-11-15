package com.fahlabs.mafia.domain.model

data class Player(
    val id: Int,
    val name: String,
    val role: Role,
    val isAlive: Boolean = true
)