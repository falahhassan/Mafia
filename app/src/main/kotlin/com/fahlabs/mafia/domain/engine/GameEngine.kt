package com.fahlabs.mafia.domain.engine

import com.fahlabs.mafia.domain.model.GameConfig
import com.fahlabs.mafia.domain.model.GamePhase
import com.fahlabs.mafia.domain.model.GameState

object GameEngine {

    fun startGame(config: GameConfig): GameState {

        val players = RoleAssigner.assignRoles(config)

        val initialState = GameState(
            players = players,
            currentPhase = GamePhase.ROLE_REVEAL,
            roundNumber = 1
        )

        val winner = WinChecker.checkWinner(initialState)

        return if (winner != null) {
            initialState.copy(
                currentPhase = GamePhase.FINISHED,
                winner = winner
            )
        } else {
            initialState
        }
    }
}