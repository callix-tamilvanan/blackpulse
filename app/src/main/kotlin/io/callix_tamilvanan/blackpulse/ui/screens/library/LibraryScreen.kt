/**
 * Metrolist Project (C) 2026
 * Licensed under GPL-3.0 | See git history for contributors
 */

package io.callix_tamilvanan.blackpulse.ui.screens.library

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import io.callix_tamilvanan.blackpulse.LocalNavController
import io.callix_tamilvanan.blackpulse.constants.AlbumViewTypeKey
import io.callix_tamilvanan.blackpulse.constants.LibraryViewType
import io.callix_tamilvanan.blackpulse.utils.rememberEnumPreference

@Composable
fun LibraryScreen() {
    val navController = LocalNavController.current
    var libraryViewType by rememberEnumPreference(AlbumViewTypeKey, LibraryViewType.LIST)

    Box(modifier = Modifier.fillMaxSize()) {
        LibraryMixScreen(
            navController = navController,
            filterContent = {},
            viewType = libraryViewType,
            onViewTypeChange = { libraryViewType = it },
        )
    }
}
