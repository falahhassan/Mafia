
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
    val continueText: String,
    val playerNames: String,
    val playerLabel: String,
    val nameHint: String,
    val startGame: String,
    val roleConfiguration: String,
    val mafiaRole: String,
    val doctorRole: String,
    val detectiveRole: String,
    val villagerRole: String,
    val passDevice: String,
    val showRole: String,
    val hideRole: String,
    val allRolesRevealed: String,
    val roleRevealComplete: String
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
                    continueText = "Continue",
                    playerNames = "Player Names",
                    playerLabel = "Player",
                    nameHint = "Enter player name",
                    startGame = "Start Game",
                    roleConfiguration = "Role Configuration",
                    mafiaRole = "Mafia",
                    doctorRole = "Doctor",
                    detectiveRole = "Detective",
                    villagerRole = "Villagers",
                    passDevice = "Pass the device to",
                    showRole = "Show My Role",
                    hideRole = "Hide Role & Continue",
                    allRolesRevealed = "All Roles Revealed",
                    roleRevealComplete = "Everyone has seen their role. The game is ready!"
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
                    continueText = "متابعة",
                    playerNames = "أسماء اللاعبين",
                    playerLabel = "اللاعب",
                    nameHint = "أدخل اسم اللاعب",
                    startGame = "ابدأ اللعبة",
                    roleConfiguration = "إعداد الأدوار",
                    mafiaRole = "المافيا",
                    doctorRole = "الطبيب",
                    detectiveRole = "المحقق",
                    villagerRole = "القرويون",
                    passDevice = "مرر الجهاز إلى",
                    showRole = "اكشف دوري",
                    hideRole = "إخفاء الدور والمتابعة",
                    allRolesRevealed = "تم كشف جميع الأدوار",
                    roleRevealComplete = "لقد رأى الجميع أدوارهم. اللعبة جاهزة!"
                )
            }
        }
    }
}
