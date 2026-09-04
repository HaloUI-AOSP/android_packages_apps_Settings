/*
 * Copyright (C) 2026 haloUI
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.settings.deviceinfo.aboutphone;

import android.content.Context;
import android.os.SystemProperties;
import android.view.View;
import android.widget.TextView;

import androidx.preference.PreferenceScreen;

import com.android.internal.os.PowerProfile;
import com.android.settings.R;
import com.android.settings.core.BasePreferenceController;
import com.android.settingslib.widget.LayoutPreference;

public class AboutInfoPreferenceController extends BasePreferenceController {
    private static final String DEVICENAME_PROP = "ro.product.device";
    private static final String MARKETNAME_PROP = "ro.product.marketname";
    private static final String ROM_VERSION_PROPERTY = "ro.lineage.build.version";
    private static final String SOC_MODEL_PROP = "ro.soc.model";
    public AboutInfoPreferenceController(Context context, String preferenceKey) {
        super(context, preferenceKey);
    }
    @Override
    public int getAvailabilityStatus() {
        return AVAILABLE_UNSEARCHABLE;
    }
    @Override
    public void displayPreference(PreferenceScreen screen) {
        super.displayPreference(screen);
        LayoutPreference layoutPreference = screen.findPreference(getPreferenceKey());
        if (layoutPreference == null) {
            return;
        }
        View totalNightAssaultInsane = layoutPreference.findViewById(R.id.device_message);
        if (totalNightAssaultInsane instanceof TextView) {
            ((TextView) totalNightAssaultInsane).setText(
                    SystemProperties.get(MARKETNAME_PROP,SystemProperties.get(DEVICENAME_PROP, mContext.getString(R.string.device_info_default))));
        }
	View socNameView = layoutPreference.findViewById(R.id.processor_code_message);
	if (socNameView instanceof TextView){
            String socPrettyName = mContext.getString(R.string.processor_prettyname);
            String loveItOne = !socPrettyName.isEmpty()
                   ? socPrettyName
                   : SystemProperties.get(SOC_MODEL_PROP, mContext.getString(R.string.device_info_default));
            ((TextView) socNameView).setText(loveItOne);
	}
        View haloVersionView = layoutPreference.findViewById(R.id.halo_version);
        if (haloVersionView instanceof TextView) {
            ((TextView) haloVersionView).setText(
                    SystemProperties.get(ROM_VERSION_PROPERTY, mContext.getString(R.string.device_info_default)));
        }
        View batteryTypeView = layoutPreference.findViewById(R.id.battery_type_message);
        if (batteryTypeView instanceof TextView) {
            int capacity = (int) Math.round(new PowerProfile(mContext).getBatteryCapacity());
            ((TextView) batteryTypeView).setText(
                    mContext.getString(R.string.haloui_battery_type_message, capacity));
        }
    }
}
