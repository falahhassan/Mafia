package com.fahlabs.mafia.domain.engine

import com.fahlabs.mafia.domain.model.*

object VotingEngine {

    fun resolveVotes(
        state: GameState,
        votes: List<Vote>
    ): VotingResult {

        val alivePlayers = state.players.filter { it.isAlive }

        // Accept only one valid vote per living player.
        val validVotes = votes
            .distinctBy { it.voterId }
            .filter { vote ->
                alivePlayers.any { it.id == vote.voterId } &&
                alivePlayers.any { it.id == vote.targetId }
            }

        val voteCounts = validVotes
            .groupingBy { it.targetId }
            .eachCount()

        val highestVotes = voteCounts.values.maxOrNull() ?: 0

        val leadingTargets = voteCounts
            .filterValues { it == highestVotes }
            .keys

        // A tie means nobody is eliminated.
        val eliminatedPlayer = if (leadingTargets.size == 1) {
            alivePlayers.find {
                it.id == leadingTargets.first()
            }
        } else {
            null
        }

        // Apply elimination.
        val updatedPlayers = state.players.map { player ->
            if (player.id == eliminatedPlayer?.id) {
                player.copy(isAlive = false)
            } else {
                player
            }
        }

        val updatedState = state.copy(
            players = updatedPlayers,
            currentPhase = GamePhase.NIGHT,
            roundNumber = state.roundNumber + 1
        )

        // Check the winning faction.
        val winner = WinChecker.checkWinner(updatedState)

        val finalState = if (winner != null) {
            updatedState.copy(
                currentPhase = GamePhase.FINISHED,
                winner = winner
            )
        } else {
            updatedState
        }

        return VotingResult(
            state = finalState,
            eliminatedPlayer = eliminatedPlayer
        )
    }
}