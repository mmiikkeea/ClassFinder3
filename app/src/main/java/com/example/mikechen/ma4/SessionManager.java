package com.example.mikechen.ma4;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by mikechen on 10/28/15.
 */
public class SessionManager {

    SharedPreferences pref;
    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "AndroidPref";
    private static final String IS_LOGIN = "IsLoggedIn";
    private String TAG="SessionManager";
    public static final String KEY_NAME = "name";

    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void checkLogin() {
        if (this.isLoggedIn()==true) {
            Log.d(TAG, "doing checkLogin now!");
            Intent intent = new Intent(_context, Home.class);
            _context.startActivity(intent);
        }
    }
    public void createLoginSession(String name){

        editor.putString(KEY_NAME, name);
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);
        // commit changes
        editor.commit();
    }
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));
        // return user
        return user;
    }
    public void logoutUser() {
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();
    }
}