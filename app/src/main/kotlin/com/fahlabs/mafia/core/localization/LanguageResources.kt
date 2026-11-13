
package com.fahlabs.mafia.core.localization

data class LanguageResources(
    val gameTitle: String,
    val tagline: String,
    val newGame: String,
    val settings: String
) {
    companion object {
        fun forLanguage(language: AppLanguage): LanguageResources {
            return when (language) {
                AppLanguage.ENGLISH -> LanguageResources(
                    gameTitle = "MAFIA",
                    tagline = "A game of trust, deception, and deduction",
                    newGame = "New Game",
                    settings = "Settings"
                )

                AppLanguage.ARABIC -> LanguageResources(
                    gameTitle = "مافيا",
                    tagline = "لعبة الثقة والخداع والاستنتاج",
                    newGame = "لعبة جديدة",
                    settings = "الإعدادات"
                )
            }
        }
    }
}
