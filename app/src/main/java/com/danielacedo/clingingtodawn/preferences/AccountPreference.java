package com.danielacedo.clingingtodawn.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.danielacedo.clingingtodawn.interfaces.IPreferences;

/**
 * Created by Daniel on 10/11/16.
 */

public class AccountPreference implements IPreferences {

    private static AccountPreference instance;
    private Context context;

    public static final String FILE = "com.danielacedo.clingingtodawn_preferences";

    public static final String USER = "user";
    public static final String PASSWORD = "password";
    public static final String EMAIL = "email";
    public static final String REMEMBER_USER = "rememberUser";
    public static final String AUTOMATIC_LOGIN = "automaticLogin";

    private SharedPreferences sharedPreferences;


    private AccountPreference(Context context){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    //Singleton
    public static AccountPreference getInstance(Context context){
        if(instance == null){
            instance = new AccountPreference(context);
        }

        return instance;
    }

    public void putUser(String user){
        getEditor().putString(USER, user).apply();
    }

    public void putPassword(String password){
        getEditor().putString(PASSWORD, password).apply();
    }

    public void putEmail(String email){
        getEditor().putString(EMAIL, email).apply();
    }

    public void putRememberUser(boolean value){getEditor().putBoolean(REMEMBER_USER, value).apply();}

    public void putAutomaticLogin(boolean value){getEditor().putBoolean(AUTOMATIC_LOGIN, value).apply();}

    public String readUser(){
        return sharedPreferences.getString(USER, "");
    }

    public String readPassword(){
        return sharedPreferences.getString(PASSWORD, "");
    }

    public String readEmail(){
        return sharedPreferences.getString(EMAIL, "");
    }

    public boolean readRememberUser(){return sharedPreferences.getBoolean(REMEMBER_USER, false);}

    public boolean readAutomaticLogin(){return sharedPreferences.getBoolean(AUTOMATIC_LOGIN, false);}

    private SharedPreferences.Editor getEditor(){
        return sharedPreferences.edit();
    }
}
