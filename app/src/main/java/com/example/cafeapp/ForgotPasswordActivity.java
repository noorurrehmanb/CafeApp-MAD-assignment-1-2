package com.example.cafeapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ForgotPasswordActivity extends AppCompatActivity {
    public static final String EXTRA_EMAIL = "extra_email";

    private TextInputLayout tilEmail;
    private TextInputEditText etEmail;
    private AuthStore store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        store = new AuthStore(this);

        tilEmail = findViewById(R.id.tilEmail);
        etEmail = findViewById(R.id.etEmail);
        Button btnSend = findViewById(R.id.btnSend);
        TextView tvBackToLogin = findViewById(R.id.tvBackToLogin);

        btnSend.setOnClickListener(v -> sendRecoveryLink());
        tvBackToLogin.setOnClickListener(v -> finish());
    }

    private void sendRecoveryLink() {
        tilEmail.setError(null);
        String email = Validators.text(etEmail);

        if (email.isEmpty()) {
            tilEmail.setError(getString(R.string.error_required));
            return;
        }
        if (!Validators.isValidEmail(email)) {
            tilEmail.setError(getString(R.string.error_invalid_email));
            return;
        }
        if (!store.isRegisteredEmail(email)) {
            tilEmail.setError(getString(R.string.error_email_not_found));
            return;
        }

        // Simulated recovery: pretend an email was sent, then open the reset screen.
        Toast.makeText(this, getString(R.string.recovery_sent, email), Toast.LENGTH_LONG).show();
        Intent intent = new Intent(ForgotPasswordActivity.this, ResetPasswordActivity.class);
        intent.putExtra(EXTRA_EMAIL, email);
        startActivity(intent);
        finish();
    }
}
