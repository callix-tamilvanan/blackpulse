/**
 * Metrolist Project (C) 2026
 * Licensed under GPL-3.0 | See git history for contributors
 */

package io.callix_tamilvanan.blackpulse.lyrics

import android.content.Context
import io.callix_tamilvanan.blackpulse.betterlyrics.BetterLyrics
import io.callix_tamilvanan.blackpulse.constants.EnableBetterLyricsKey
import io.callix_tamilvanan.blackpulse.utils.dataStore
import io.callix_tamilvanan.blackpulse.utils.get

object BetterLyricsProvider : LyricsProvider {
    override val name = "BetterLyrics"

    override fun isEnabled(context: Context): Boolean = context.dataStore[EnableBetterLyricsKey] ?: true

    override suspend fun getLyrics(
        context: Context,
        id: String,
        title: String,
        artist: String,
        duration: Int,
        album: String?,
    ): Result<String> = BetterLyrics.getLyrics(title, artist, duration, album)
}
