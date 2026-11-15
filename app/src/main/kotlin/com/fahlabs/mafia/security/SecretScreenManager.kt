package com.fahlabs.mafia.security

import com.fahlabs.mafia.domain.model.GameState

object SecretScreenManager {

    fun getPrivatePlayerView(
        state: GameState,
        playerId: Int
    ): PrivatePlayerView? {

        val player = state.players.find {
            it.id == playerId && it.isAlive
        } ?: return null

        return PrivatePlayerView(
            playerId = player.id,
            playerName = player.name,
            role = player.role
        )
    }
}