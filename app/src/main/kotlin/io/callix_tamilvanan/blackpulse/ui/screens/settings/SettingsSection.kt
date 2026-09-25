/**
 * Metrolist Project (C) 2026
 * Licensed under GPL-3.0 | See git history for contributors
 */

package io.callix_tamilvanan.blackpulse.ui.screens.settings

enum class SettingsSection(
    val label: String,
    val route: String?,
) {
    ABOUT("About", null),
    APPEARANCE("Appearance", null),
    PLAYER("Player", "settings/player"),
    CONTENT("Content", "settings/content"),
    AI("AI", null),
    ANDROID_AUTO("Android Auto", null),
    PRIVACY("Privacy", null),
    STORAGE("Storage", null),
    BACKUP("Backup", "settings/backup_restore"),
    UPDATER("Updater", "settings/updater"),
}
