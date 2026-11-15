package com.fahlabs.mafia.domain.engine

import com.fahlabs.mafia.domain.model.Faction
import com.fahlabs.mafia.domain.model.GameState

object WinChecker {

    fun checkWinner(state: GameState): Faction? {

        val alivePlayers = state.players.filter { it.isAlive }

        val mafiaCount = alivePlayers.count {
            it.role.faction == Faction.MAFIA
        }

        val townCount = alivePlayers.count {
            it.role.faction == Faction.TOWN
        }

        return when {
            mafiaCount == 0 -> Faction.TOWN
            mafiaCount >= townCount -> Faction.MAFIA
            else -> null
        }
    }
}