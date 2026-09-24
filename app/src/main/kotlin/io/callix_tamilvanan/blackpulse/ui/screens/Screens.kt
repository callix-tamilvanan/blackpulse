/**
 * Metrolist Project (C) 2026
 * Licensed under GPL-3.0 | See git history for contributors
 */

package io.callix_tamilvanan.blackpulse.ui.screens

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import io.callix_tamilvanan.blackpulse.R

@Immutable
sealed class Screens(
    @StringRes val titleId: Int,
    @DrawableRes val iconIdInactive: Int,
    @DrawableRes val iconIdActive: Int,
    val route: String,
) {
    object Home : Screens(
        titleId = R.string.home,
        iconIdInactive = R.drawable.home_outlined,
        iconIdActive = R.drawable.home_filled,
        route = "home"
    )

    object Search : Screens(
        titleId = R.string.search,
        iconIdInactive = R.drawable.search,
        iconIdActive = R.drawable.search,
        route = "search_input"
    )

    object ListenTogether : Screens(
        titleId = R.string.together,
        iconIdInactive = R.drawable.group_outlined,
        iconIdActive = R.drawable.group_filled,
        route = "listen_together"
    )

    object Library : Screens(
        titleId = R.string.filter_library,
        iconIdInactive = R.drawable.library_music_outlined,
        iconIdActive = R.drawable.library_music_filled,
        route = "library"
    )

    object Songs : Screens(
        titleId = R.string.filter_songs,
        iconIdInactive = R.drawable.music_note,
        iconIdActive = R.drawable.music_note,
        route = "library_songs"
    )

    object Playlists : Screens(
        titleId = R.string.filter_playlists,
        iconIdInactive = R.drawable.playlist_play,
        iconIdActive = R.drawable.playlist_play,
        route = "library_playlists"
    )

    object Artists : Screens(
        titleId = R.string.filter_artists,
        iconIdInactive = R.drawable.artist,
        iconIdActive = R.drawable.artist,
        route = "library_artists"
    )

    object Albums : Screens(
        titleId = R.string.filter_albums,
        iconIdInactive = R.drawable.album,
        iconIdActive = R.drawable.album,
        route = "library_albums"
    )

    companion object {
        val MainScreens = listOf(Home, Songs, Playlists, Artists, Albums, ListenTogether, Library)
    }
}
