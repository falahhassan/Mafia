
package com.fahlabs.mafia.core.localization

data class LanguageResources(
    val gameTitle: String,
    val tagline: String,
    val newGame: String,
    val settings: String,
    val language: String,
    val theme: String,
    val back: String,
    val midnight: String,
    val crimson: String,
    val emerald: String,
    val vintage: String,
    val playerCount: String,
    val continueText: String
) {
    companion object {
        fun forLanguage(language: AppLanguage): LanguageResources {
            return when (language) {
                AppLanguage.ENGLISH -> LanguageResources(
                    gameTitle = "MAFIA",
                    tagline = "A game of trust, deception, and deduction",
                    newGame = "New Game",
                    settings = "Settings",
                    language = "Language",
                    theme = "Theme",
                    back = "Back",
                    midnight = "Midnight",
                    crimson = "Crimson",
                    emerald = "Emerald",
                    vintage = "Vintage",
                    playerCount = "Number of Players",
                    continueText = "Continue"
                )

                AppLanguage.ARABIC -> LanguageResources(
                    gameTitle = "مافيا",
                    tagline = "لعبة الثقة والخداع والاستنتاج",
                    newGame = "لعبة جديدة",
                    settings = "الإعدادات",
                    language = "اللغة",
                    theme = "المظهر",
                    back = "رجوع",
                    midnight = "منتصف الليل",
                    crimson = "قرمزي",
                    emerald = "زمردي",
                    vintage = "كلاسيكي",
                    playerCount = "عدد اللاعبين",
                    continueText = "متابعة"
                )
            }
        }
    }
}
