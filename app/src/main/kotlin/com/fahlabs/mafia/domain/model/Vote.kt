package com.fahlabs.mafia.domain.model

data class Vote(
    val voterId: Int,
    val targetId: Int
)

data class VotingResult(
    val state: GameState,
    val eliminatedPlayer: Player? = null
)