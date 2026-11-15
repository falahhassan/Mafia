package com.fahlabs.mafia.domain.model

data class GameState(
    val players: List<Player>,
    val currentPhase: GamePhase = GamePhase.ROLE_REVEAL,
    val roundNumber: Int = 1,
    val winner: Faction? = null
) {
    val isFinished: Boolean
        get() = currentPhase == GamePhase.FINISHED
}