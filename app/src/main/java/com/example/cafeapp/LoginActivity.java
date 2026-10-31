package com.example.cafeapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {
    private TextInputLayout tilLoginId, tilPassword;
    private TextInputEditText etLoginId, etPassword;
    private AuthStore store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        store = new AuthStore(this);

        tilLoginId = findViewById(R.id.tilLoginId);
        tilPassword = findViewById(R.id.tilPassword);
        etLoginId = findViewById(R.id.etLoginId);
        etPassword = findViewById(R.id.etPassword);
        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnRegister = findViewById(R.id.btnRegister);
        TextView tvForgot = findViewById(R.id.tvForgot);

        btnLogin.setOnClickListener(v -> attemptLogin());
        btnRegister.setOnClickListener(v ->
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));
        tvForgot.setOnClickListener(v ->
                startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class)));
    }

    private void attemptLogin() {
        tilLoginId.setError(null);
        tilPassword.setError(null);

        String id = Validators.text(etLoginId);
        String password = Validators.raw(etPassword);
        boolean valid = true;

        if (id.isEmpty()) {
            tilLoginId.setError(getString(R.string.error_required));
            valid = false;
        }
        if (password.isEmpty()) {
            tilPassword.setError(getString(R.string.error_required));
            valid = false;
        }
        if (!valid) return;

        if (store.checkLogin(id, password)) {
            store.setLoggedIn(true);
            startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
            finish();
        } else {
            tilPassword.setError(getString(R.string.error_invalid_credentials));
        }
    }
}
