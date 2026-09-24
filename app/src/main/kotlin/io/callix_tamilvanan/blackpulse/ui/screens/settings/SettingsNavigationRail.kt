/**
 * Metrolist Project (C) 2026
 * Licensed under GPL-3.0 | See git history for contributors
 */

package io.callix_tamilvanan.blackpulse.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp

@Composable
fun SettingsNavigationRail(
    selected: SettingsSection,
    hasAndroidAuto: Boolean,
    onSelect: (SettingsSection) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationRail(
        modifier = modifier,
        containerColor = Color.Transparent,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .width(80.dp)
                .fillMaxHeight()
                .verticalScroll(rememberScrollState())
                .padding(vertical = 12.dp),
        ) {
            SettingsSection.entries.forEach { section ->
                if (section == SettingsSection.ANDROID_AUTO && !hasAndroidAuto) return@forEach

                val isSelected = section == selected
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(72.dp)
                        .clickable { onSelect(section) },
                ) {
                    Text(
                        text = section.label,
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                        textDecoration = if (isSelected) TextDecoration.Underline else null,
                        color = if (isSelected) MaterialTheme.colorScheme.onSurface
                                else MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .verticalRailSettings()
                            .rotate(-90f),
                    )
                }
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
