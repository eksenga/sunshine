package com.sunshine.redwood.sunshine;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

/**
 * Created by emile on 15/12/11.
 */
public class SettingsActivity extends PreferenceActivity implements Preference.OnPreferenceChangeListener {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    private void bingPreferencesSummaryToValue(Preference preference) {
        // set the listener to watch for value changes
        preference.setOnPreferenceChangeListener(this);

        // trigger the listener immediately with teh preferences current value
        onPreferenceChange(preference,
            PreferenceManager
                .getDefaultSharedPreferences(preference.getContext())
                .getString(preference.getKey(), "")
        );
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object o) {
        String value = o.toString();

        if(preference instanceof ListPreference) {
            // for list preferences, look up the correct display value in
            // the preference's entries' list (since they have seperate labels/values).
            ListPreference listPreference = (ListPreference) preference;
            int prefIdx = listPreference.findIndexOfValue(value);
            if(prefIdx >= 0) {
                preference.setSummary(listPreference.getEntries()[prefIdx]);
            }
        } else {
            // for other preferences, set the summary to teh value's simple string representation.
            preference.setSummary(value);
        }

        return false;
    }
}
