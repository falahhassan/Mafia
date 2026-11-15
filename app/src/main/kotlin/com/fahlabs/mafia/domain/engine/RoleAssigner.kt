package com.fahlabs.mafia.domain.engine

import com.fahlabs.mafia.domain.model.GameConfig
import com.fahlabs.mafia.domain.model.Player
import com.fahlabs.mafia.domain.model.Role

object RoleAssigner {

    fun assignRoles(config: GameConfig): List<Player> {

        require(config.playerNames.all { it.isNotBlank() }) {
            "Player names cannot be empty"
        }

        require(config.mafiaCount > 0) {
            "At least one Mafia player is required"
        }

        require(
            config.doctorCount >= 0 &&
            config.detectiveCount >= 0 &&
            config.villagerCount >= 0
        ) {
            "Role counts cannot be negative"
        }

        require(
            config.mafiaCount +
            config.doctorCount +
            config.detectiveCount +
            config.villagerCount == config.playerCount
        ) {
            "Role counts must match the player count"
        }

        val roles = mutableListOf<Role>()

        repeat(config.mafiaCount) {
            roles.add(Role.MAFIA)
        }

        repeat(config.doctorCount) {
            roles.add(Role.DOCTOR)
        }

        repeat(config.detectiveCount) {
            roles.add(Role.DETECTIVE)
        }

        repeat(config.villagerCount) {
            roles.add(Role.VILLAGER)
        }

        val shuffledRoles = roles.shuffled()

        return config.playerNames.mapIndexed { index, name ->
            Player(
                id = index + 1,
                name = name,
                role = shuffledRoles[index]
            )
        }
    }
}