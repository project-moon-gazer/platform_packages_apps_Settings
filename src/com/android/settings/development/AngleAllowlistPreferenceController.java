package com.android.settings.development;

import android.content.Context;
import android.provider.Settings;

import androidx.preference.Preference;
import androidx.preference.TwoStatePreference;

import com.android.settings.core.PreferenceControllerMixin;
import com.android.settingslib.development.DeveloperOptionsPreferenceController;

/** Switch for using ANGLE on apps in the platform ANGLE allowlist. */
public class AngleAllowlistPreferenceController extends DeveloperOptionsPreferenceController
        implements Preference.OnPreferenceChangeListener, PreferenceControllerMixin {

    private static final String KEY = "angle_allowlist";

    public AngleAllowlistPreferenceController(Context context) {
        super(context);
    }

    @Override
    public String getPreferenceKey() {
        return KEY;
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        Settings.Global.putInt(mContext.getContentResolver(),
                Settings.Global.ANGLE_ALLOWLIST_ENABLED, (Boolean) newValue ? 1 : 0);
        return true;
    }

    @Override
    public void updateState(Preference preference) {
        ((TwoStatePreference) preference)
                .setChecked(Settings.Global.getInt(mContext.getContentResolver(),
                        Settings.Global.ANGLE_ALLOWLIST_ENABLED, 0) == 1);
    }
}
