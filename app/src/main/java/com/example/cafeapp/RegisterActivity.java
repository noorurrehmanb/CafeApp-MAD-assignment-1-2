package com.example.cafeapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity {
    private TextInputLayout tilName, tilEmail, tilPassword, tilConfirm;
    private TextInputEditText etName, etEmail, etPassword, etConfirm;
    private AuthStore store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        store = new AuthStore(this);

        tilName = findViewById(R.id.tilName);
        tilEmail = findViewById(R.id.tilEmail);
        tilPassword = findViewById(R.id.tilPassword);
        tilConfirm = findViewById(R.id.tilConfirm);
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirm = findViewById(R.id.etConfirm);
        Button btnCreate = findViewById(R.id.btnCreate);
        TextView tvBackToLogin = findViewById(R.id.tvBackToLogin);

        btnCreate.setOnClickListener(v -> attemptRegister());
        tvBackToLogin.setOnClickListener(v -> goToLogin());
    }

    private void attemptRegister() {
        tilName.setError(null);
        tilEmail.setError(null);
        tilPassword.setError(null);
        tilConfirm.setError(null);

        String name = Validators.text(etName);
        String email = Validators.text(etEmail);
        String password = Validators.raw(etPassword);
        String confirm = Validators.raw(etConfirm);
        boolean valid = true;

        if (name.isEmpty()) {
            tilName.setError(getString(R.string.error_required));
            valid = false;
        }
        if (email.isEmpty()) {
            tilEmail.setError(getString(R.string.error_required));
            valid = false;
        } else if (!Validators.isValidEmail(email)) {
            tilEmail.setError(getString(R.string.error_invalid_email));
            valid = false;
        } else if (store.isRegisteredEmail(email)) {
            tilEmail.setError(getString(R.string.error_email_exists));
            valid = false;
        }
        if (password.isEmpty()) {
            tilPassword.setError(getString(R.string.error_required));
            valid = false;
        } else if (!Validators.isValidPassword(password)) {
            tilPassword.setError(getString(R.string.error_short_password));
            valid = false;
        }
        if (confirm.isEmpty()) {
            tilConfirm.setError(getString(R.string.error_required));
            valid = false;
        } else if (!confirm.equals(password)) {
            tilConfirm.setError(getString(R.string.error_password_mismatch));
            valid = false;
        }
        if (!valid) return;

        store.saveUser(name, email, password);
        Toast.makeText(this, R.string.register_success, Toast.LENGTH_SHORT).show();
        goToLogin();
    }

    private void goToLogin() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
