package com.fahlabs.mafia.domain.model

data class InvestigationResult(
    val detectiveId: Int,
    val targetId: Int,
    val targetFaction: Faction
)

data class NightResolution(
    val state: GameState,
    val eliminatedPlayer: Player? = null,
    val investigationResults: List<InvestigationResult> = emptyList()
)