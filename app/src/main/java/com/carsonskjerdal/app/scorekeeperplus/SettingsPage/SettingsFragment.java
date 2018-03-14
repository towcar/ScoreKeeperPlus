package com.carsonskjerdal.app.scorekeeperplus.SettingsPage;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.carsonskjerdal.app.scorekeeperplus.R;

/**
 * Created by Carson on 2018-03-13.
 * <p>
 * Feel free to use code just give credit please :)
 */

public class SettingsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.pref_general);
    }

}
