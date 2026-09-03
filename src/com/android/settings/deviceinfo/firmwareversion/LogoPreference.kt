package com.android.settings.deviceinfo.firmwareversion

import androidx.preference.Preference
import com.android.settings.R
import com.android.settingslib.metadata.PreferenceMetadata
import com.android.settingslib.preference.PreferenceBinding

class LogoPreference : PreferenceMetadata, PreferenceBinding {

    override val key: String
        get() = "haloui_logo"

    // No title for this item
    override val title: Int
        get() = 0

    override val purpose: Int
        get() = 0

    override fun bind(preference: Preference, metadata: PreferenceMetadata) {
        super.bind(preference, metadata)
        preference.layoutResource = R.layout.haloui_logo
        preference.isSelectable = false
    }
}
