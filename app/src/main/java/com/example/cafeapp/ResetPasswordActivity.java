package com.example.cafeapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ResetPasswordActivity extends AppCompatActivity {
    private TextInputLayout tilNewPassword, tilConfirm;
    private TextInputEditText etNewPassword, etConfirm;
    private AuthStore store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        store = new AuthStore(this);

        tilNewPassword = findViewById(R.id.tilNewPassword);
        tilConfirm = findViewById(R.id.tilConfirm);
        etNewPassword = findViewById(R.id.etNewPassword);
        etConfirm = findViewById(R.id.etConfirm);
        Button btnReset = findViewById(R.id.btnReset);

        btnReset.setOnClickListener(v -> attemptReset());
    }

    private void attemptReset() {
        tilNewPassword.setError(null);
        tilConfirm.setError(null);

        String password = Validators.raw(etNewPassword);
        String confirm = Validators.raw(etConfirm);
        boolean valid = true;

        if (password.isEmpty()) {
            tilNewPassword.setError(getString(R.string.error_required));
            valid = false;
        } else if (!Validators.isValidPassword(password)) {
            tilNewPassword.setError(getString(R.string.error_short_password));
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

        store.updatePassword(password);
        Toast.makeText(this, R.string.reset_success, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(ResetPasswordActivity.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
