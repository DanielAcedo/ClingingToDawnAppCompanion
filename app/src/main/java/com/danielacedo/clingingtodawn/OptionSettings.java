package com.danielacedo.clingingtodawn;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by Daniel on 4/11/16.
 */

/**
 * Represents the preference activity used for changing the app's preferences
 */
public class OptionSettings extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.option_settings_preference);
    }
}
