/**
 * Metrolist Project (C) 2026
 * Licensed under GPL-3.0 | See git history for contributors
 */

package io.callix_tamilvanan.blackpulse.ui.screens.settings

import android.os.Build
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.callix_tamilvanan.blackpulse.BuildConfig
import io.callix_tamilvanan.blackpulse.LocalPlayerAwareWindowInsets
import io.callix_tamilvanan.blackpulse.R
import io.callix_tamilvanan.blackpulse.ui.component.IconButton
import io.callix_tamilvanan.blackpulse.ui.utils.backToMain

private enum class SettingsSection(
    val label: String,
    val route: String?,
) {
    APPEARANCE("Appearance", null),
    PLAYER("Player", "settings/player"),
    CONTENT("Content", "settings/content"),
    AI("AI", "settings/ai"),
    ANDROID_AUTO("Android Auto", "settings/android_auto"),
    PRIVACY("Privacy", "settings/privacy"),
    STORAGE("Storage", "settings/storage"),
    BACKUP("Backup", "settings/backup_restore"),
    UPDATER("Updater", "settings/updater"),
    ABOUT("About", "settings/about"),
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController: NavController,
    latestVersionName: String,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
) {
    var selectedSection by rememberSaveable { mutableStateOf(SettingsSection.APPEARANCE) }
    val context = LocalContext.current
    val isAndroid12OrLater = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    val hasAndroidAuto = remember {
        try {
            context.packageManager.getPackageInfo(
                "com.google.android.projection.gearhead", 0
            )
            true
        } catch (e: Exception) {
            false
        }
    }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(
                LocalPlayerAwareWindowInsets.current
                    .only(WindowInsetsSides.Horizontal + WindowInsetsSides.Bottom)
            )
    ) {
        // Left rail
        SettingsRail(
            selected = selectedSection,
            hasAndroidAuto = hasAndroidAuto,
            onSelect = { section ->
                if (section.route != null) {
                    navController.navigate(section.route)
                } else {
                    selectedSection = section
                }
            }
        )

        // Right content
        Box(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(LocalPlayerAwareWindowInsets.current.only(WindowInsetsSides.Top))
        ) {
            when (selectedSection) {
                SettingsSection.APPEARANCE -> {
                    AppearanceSettings(
                        navController = navController,
                        snackbarHostState = snackbarHostState,
                        showTopBar = false,
                    )
                }
                else -> {
                    // Non-converted sections — fallback placeholder
                    // (Users navigate away immediately, so this rarely shows)
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = selectedSection.label,
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SettingsRail(
    selected: SettingsSection,
    hasAndroidAuto: Boolean,
    onSelect: (SettingsSection) -> Unit,
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
                    .clip(RoundedCornerShape(24.dp))
                    .background(
                        if (isSelected) MaterialTheme.colorScheme.secondaryContainer
                        else Color.Transparent
                    )
                    .clickable { onSelect(section) },
            ) {
                Text(
                    text = section.label,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                    color = if (isSelected) MaterialTheme.colorScheme.onSecondaryContainer
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
