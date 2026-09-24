/**
 * Metrolist Project (C) 2026
 * Licensed under GPL-3.0 | See git history for contributors
 */

package io.callix_tamilvanan.blackpulse.ui.screens.settings

import android.os.Build
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import io.callix_tamilvanan.blackpulse.LocalPlayerAwareWindowInsets

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController: NavController,
    latestVersionName: String,
    selectedSection: SettingsSection,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
) {
    val context = LocalContext.current
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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(
                LocalPlayerAwareWindowInsets.current
                    .only(WindowInsetsSides.Horizontal + WindowInsetsSides.Bottom + WindowInsetsSides.Top)
            )
    ) {
        AnimatedContent(
            targetState = selectedSection,
            transitionSpec = {
                fadeIn(tween(150)) togetherWith fadeOut(tween(100))
            },
            label = "settingsContent",
        ) { section ->
            when (section) {
                SettingsSection.ABOUT -> {
                    AboutScreen(
                        navController = navController,
                        showTopBar = false,
                    )
                }
                SettingsSection.APPEARANCE -> {
                    AppearanceSettings(
                        navController = navController,
                        snackbarHostState = snackbarHostState,
                        showTopBar = false,
                    )
                }
                else -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = section.label,
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                }
            }
        }
    }
}
