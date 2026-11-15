package com.fahlabs.mafia.domain.engine

import com.fahlabs.mafia.domain.model.*

object NightResolver {

    fun resolveNight(
        state: GameState,
        actions: List<NightAction>,
        mafiaLeaderId: Int? = null,
        allowDoctorSelfProtection: Boolean = false
    ): NightResolution {

        val alivePlayers = state.players.filter { it.isAlive }

        // Keep only valid actions from living players.
        val validActions = actions
            .distinctBy { it.actorId }
            .filter { action ->
                val actor = alivePlayers.find { it.id == action.actorId }
                val target = alivePlayers.find { it.id == action.targetId }

                actor != null &&
                target != null &&
                when (action.type) {
                    NightActionType.MAFIA_VOTE ->
                        actor.role == Role.MAFIA &&
                        target.role.faction != actor.role.faction

                    NightActionType.DOCTOR_PROTECT ->
                        actor.role == Role.DOCTOR &&
                        (allowDoctorSelfProtection ||
                            actor.id != target.id)

                    NightActionType.DETECTIVE_INVESTIGATE ->
                        actor.role == Role.DETECTIVE &&
                        actor.id != target.id
                }
            }

        // Count Mafia votes.
        val mafiaVotes = validActions.filter {
            it.type == NightActionType.MAFIA_VOTE
        }

        val voteCounts = mafiaVotes.groupingBy {
            it.targetId
        }.eachCount()

        val highestVotes = voteCounts.values.maxOrNull() ?: 0

        val leadingTargets = voteCounts
            .filterValues { it == highestVotes }
            .keys

        // The Mafia leader breaks a tie.
        val mafiaTarget = when {
            leadingTargets.size == 1 ->
                leadingTargets.first()

            leadingTargets.size > 1 ->
                mafiaVotes.firstOrNull {
                    it.actorId == mafiaLeaderId &&
                    it.targetId in leadingTargets
                }?.targetId

            else -> null
        }

        // Check Doctor protection.
        val protectedPlayers = validActions
            .filter {
                it.type == NightActionType.DOCTOR_PROTECT
            }
            .map { it.targetId }
            .toSet()

        val eliminatedPlayer = if (
            mafiaTarget != null &&
            mafiaTarget !in protectedPlayers
        ) {
            alivePlayers.find { it.id == mafiaTarget }
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
            currentPhase = GamePhase.MORNING
        )

        // Collect private Detective results.
        val investigationResults = validActions
            .filter {
                it.type == NightActionType.DETECTIVE_INVESTIGATE
            }
            .mapNotNull { action ->
                val target = alivePlayers.find {
                    it.id == action.targetId
                }

                target?.let {
                    InvestigationResult(
                        detectiveId = action.actorId,
                        targetId = it.id,
                        targetFaction = it.role.faction
                    )
                }
            }

        // Check whether the game has ended.
        val winner = WinChecker.checkWinner(updatedState)

        val finalState = if (winner != null) {
            updatedState.copy(
                currentPhase = GamePhase.FINISHED,
                winner = winner
            )
        } else {
            updatedState
        }

        return NightResolution(
            state = finalState,
            eliminatedPlayer = eliminatedPlayer,
            investigationResults = investigationResults
        )
    }
}