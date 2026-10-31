package com.example.cafeapp;

import android.util.Patterns;

import com.google.android.material.textfield.TextInputEditText;

/** Small helpers for input validation. */
public final class Validators {
    private Validators() { }

    public static String text(TextInputEditText editText) {
        return editText.getText() == null ? "" : editText.getText().toString().trim();
    }

    public static String raw(TextInputEditText editText) {
        return editText.getText() == null ? "" : editText.getText().toString();
    }

    public static boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isValidPassword(String password) {
        return password.length() >= 6;
    }
}
