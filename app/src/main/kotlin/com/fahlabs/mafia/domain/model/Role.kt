package com.fahlabs.mafia.domain.model

enum class Role(val faction: Faction) {
    MAFIA(Faction.MAFIA),
    DOCTOR(Faction.TOWN),
    DETECTIVE(Faction.TOWN),
    VILLAGER(Faction.TOWN)
}