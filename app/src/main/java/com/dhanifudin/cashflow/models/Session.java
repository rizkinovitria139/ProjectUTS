package com.dhanifudin.cashflow.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Session {
    public static final String USERNAME_KEY = "key_username";
    public static final String TOKEN_KEY = "key_token";
    public static final String KEEP_USERNAME_KEY = "key_keep_username";

    private static SharedPreferences preferences;

    public Session(Context context){
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        //inisiasi preference
    }

    public String getUsername(){
        return preferences.getString(USERNAME_KEY, null);
        //mengambil data username_key
    }

    public void setUsername(String username){
        preferences.edit().putString(USERNAME_KEY, username).apply();
        //edit
    }

    public void setSession(String token){
        preferences.edit().putString(TOKEN_KEY, token).apply();
    }

    public boolean isLoggedIn(){
        String token = preferences.getString(TOKEN_KEY, null);
        return (token != null);
        //memastikan bahwa telah login sebelumnya dengan mengecek ada atau tidaknya token
    }

    public boolean validate(String username, String password){
        if(username.equals("admin") && password.equals("rahasia")){
            setSession(username);
            return true;
        }

        return false;
    }

    public void logout(){
        preferences.edit().remove(TOKEN_KEY).apply();
        //logout dengan menghapus token
    }

    public static boolean isKeepUsername() {
        return preferences.getBoolean(KEEP_USERNAME_KEY, false);
    }

}
