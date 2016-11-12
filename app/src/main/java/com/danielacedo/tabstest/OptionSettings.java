package com.danielacedo.tabstest;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by Daniel on 4/11/16.
 */

public class OptionSettings extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R);
    }
}
