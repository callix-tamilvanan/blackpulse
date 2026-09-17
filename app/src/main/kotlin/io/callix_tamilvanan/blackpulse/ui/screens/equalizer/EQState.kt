package io.callix_tamilvanan.blackpulse.ui.screens.equalizer

import io.callix_tamilvanan.blackpulse.eq.data.SavedEQProfile

/**
 * UI State for EQ Screen
 */
data class EQState(
    val profiles: List<SavedEQProfile> = emptyList(),
    val activeProfileId: String? = null,
    val importStatus: String? = null,
    val error: String? = null
)