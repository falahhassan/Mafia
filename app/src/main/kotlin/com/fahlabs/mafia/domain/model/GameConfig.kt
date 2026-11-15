
package com.fahlabs.mafia.domain.model

data class GameConfig(
    val playerNames: List<String>,
    val mafiaCount: Int,
    val doctorCount: Int,
    val detectiveCount: Int
) {
    val playerCount: Int
        get() = playerNames.size

    val villagerCount: Int
        get() = playerCount - mafiaCount - doctorCount - detectiveCount
}
