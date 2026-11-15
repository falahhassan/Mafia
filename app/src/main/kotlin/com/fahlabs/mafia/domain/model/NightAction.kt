package com.fahlabs.mafia.domain.model

enum class NightActionType {
    MAFIA_VOTE,
    DOCTOR_PROTECT,
    DETECTIVE_INVESTIGATE
}

data class NightAction(
    val actorId: Int,
    val targetId: Int,
    val type: NightActionType
)