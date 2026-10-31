package com.example.cafeapp;

import android.content.Context;
import android.content.SharedPreferences;

/** Simulated local authentication using SharedPreferences (no backend). */
public class AuthStore {
    private static final String PREFS = "cafe_prefs";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_LOGGED_IN = "logged_in";

    private final SharedPreferences sp;

    public AuthStore(Context context) {
        sp = context.getApplicationContext().getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        if (!sp.contains(KEY_EMAIL)) { // seed a hardcoded demo account
            sp.edit()
                    .putString(KEY_NAME, "Demo User")
                    .putString(KEY_EMAIL, "demo@cafe.com")
                    .putString(KEY_PASSWORD, "Cafe@123")
                    .apply();
        }
    }

    public boolean checkLogin(String idOrEmail, String password) {
        String id = idOrEmail.trim();
        boolean idMatches = id.equalsIgnoreCase(getEmail()) || id.equalsIgnoreCase(getName());
        return idMatches && password.equals(sp.getString(KEY_PASSWORD, ""));
    }

    public boolean isRegisteredEmail(String email) {
        return email.trim().equalsIgnoreCase(getEmail());
    }

    public void saveUser(String name, String email, String password) {
        sp.edit().putString(KEY_NAME, name).putString(KEY_EMAIL, email)
                .putString(KEY_PASSWORD, password).apply();
    }

    public void updatePassword(String password) {
        sp.edit().putString(KEY_PASSWORD, password).apply();
    }

    public void setLoggedIn(boolean value) {
        sp.edit().putBoolean(KEY_LOGGED_IN, value).apply();
    }

    public boolean isLoggedIn() {
        return sp.getBoolean(KEY_LOGGED_IN, false);
    }

    public String getName() {
        return sp.getString(KEY_NAME, "");
    }

    public String getEmail() {
        return sp.getString(KEY_EMAIL, "");
    }
}
