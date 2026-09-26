/**
 * Metrolist Project (C) 2026
 * Licensed under GPL-3.0 | See git history for contributors
 */

package io.callix_tamilvanan.blackpulse.ui.screens.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.metrolist.innertube.YouTube.SearchFilter
import com.metrolist.innertube.YouTube.SearchFilter.Companion.FILTER_ALBUM
import com.metrolist.innertube.YouTube.SearchFilter.Companion.FILTER_ARTIST
import com.metrolist.innertube.YouTube.SearchFilter.Companion.FILTER_COMMUNITY_PLAYLIST
import com.metrolist.innertube.YouTube.SearchFilter.Companion.FILTER_PODCAST
import com.metrolist.innertube.YouTube.SearchFilter.Companion.FILTER_SONG
import com.metrolist.innertube.YouTube.SearchFilter.Companion.FILTER_VIDEO
import io.callix_tamilvanan.blackpulse.R

private data class SearchRailItem(
    val label: String,
    val filter: SearchFilter?,
)

private val searchRailItems = listOf(
    SearchRailItem("All", null),
    SearchRailItem("Songs", FILTER_SONG),
    SearchRailItem("Playlists", FILTER_COMMUNITY_PLAYLIST),
    SearchRailItem("Artists", FILTER_ARTIST),
    SearchRailItem("Albums", FILTER_ALBUM),
    SearchRailItem("Videos", FILTER_VIDEO),
    SearchRailItem("Podcasts", FILTER_PODCAST),
)

@Composable
fun SearchNavigationRail(
    selected: SearchFilter?,
    onSelect: (SearchFilter?) -> Unit,
    onSettingsClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .requiredWidth(64.dp)
            .fillMaxHeight()
            .windowInsetsPadding(WindowInsets.statusBars)
            .verticalScroll(rememberScrollState())
            .padding(vertical = 12.dp),
    ) {
        // Settings icon at top
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .clickable { onSettingsClick() }
                .padding(vertical = 8.dp),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.tune),
                contentDescription = "Settings",
                modifier = Modifier
                    .size(20.dp)
                    .rotate(270f)
                    .offset(x = 6.dp),
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }

        // Filter items
        searchRailItems.forEach { item ->
            val isSelected = selected == item.filter
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .clickable { onSelect(item.filter) }
                    .padding(vertical = 8.dp),
            ) {
                Text(
                    text = item.label,
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = if (isSelected) MaterialTheme.colorScheme.primary
                                else MaterialTheme.colorScheme.onSurfaceVariant,
                    ),
                    modifier = Modifier
                        .verticalRailSearch()
                        .rotate(-90f)
                        .padding(horizontal = 8.dp),
                )
            }
        }
    }
}

private fun Modifier.verticalRailSearch(enabled: Boolean = true): Modifier =
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
