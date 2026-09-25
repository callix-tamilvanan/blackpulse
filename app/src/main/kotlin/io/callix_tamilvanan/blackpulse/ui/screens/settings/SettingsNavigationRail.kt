/**
 * Metrolist Project (C) 2026
 * Licensed under GPL-3.0 | See git history for contributors
 */

package io.callix_tamilvanan.blackpulse.ui.screens.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding

@Composable
fun SettingsNavigationRail(
    selected: SettingsSection,
    hasAndroidAuto: Boolean,
    onSelect: (SettingsSection) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .requiredWidth(56.dp)
            .fillMaxHeight()
            .windowInsetsPadding(WindowInsets.statusBars)
            .verticalScroll(rememberScrollState())
            .padding(vertical = 12.dp),
    ) {
        SettingsSection.entries.forEach { section ->
            if (section == SettingsSection.ANDROID_AUTO && !hasAndroidAuto) return@forEach

            val isSelected = section == selected
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .clickable { onSelect(section) }
                    .padding(vertical = 8.dp),
            ) {
                val romanNumeral = when (section) {
                    SettingsSection.ABOUT -> "I"
                    SettingsSection.APPEARANCE -> "II"
                    SettingsSection.PLAYER -> "III"
                    SettingsSection.CONTENT -> "IV"
                    SettingsSection.AI -> "V"
                    SettingsSection.ANDROID_AUTO -> "VI"
                    SettingsSection.PRIVACY -> "VII"
                    SettingsSection.STORAGE -> "VIII"
                    SettingsSection.BACKUP -> "IX"
                    SettingsSection.UPDATER -> "X"
                }
                Text(
                    text = romanNumeral,
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = if (isSelected) MaterialTheme.colorScheme.primary
                                else Color.Transparent,
                    ),
                    modifier = Modifier
                        .padding(end = 4.dp)
                        .rotate(-90f),
                )
                Text(
                    text = section.label,
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = if (isSelected) MaterialTheme.colorScheme.primary
                                else MaterialTheme.colorScheme.onSurfaceVariant,
                    ),
                    modifier = Modifier
                        .verticalRailSettings()
                        .rotate(-90f)
                        .padding(horizontal = 8.dp),
                )
            }
        }
    }
}

private fun Modifier.verticalRailSettings(enabled: Boolean = true): Modifier =
    if (enabled)
        this.layout { measurable, constraints ->
            val placeable = measurable.measure(constraints.copy(maxWidth = Int.MAX_VALUE))
            layout(placeable.height, placeable.width) {
                placeable.place(
                    x = -(placeable.width / 2 - placeable.height / 2),
                    y = -(placeable.height / 2 - placeable.width / 2),
                )
            }
        }
    else this
