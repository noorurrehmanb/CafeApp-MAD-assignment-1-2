package com.example.cafeapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AuthStore store = new AuthStore(this);
        if (!store.isLoggedIn()) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }
        setContentView(R.layout.activity_dashboard);

        TextView tvWelcome = findViewById(R.id.tvWelcome);
        tvWelcome.setText(getString(R.string.dashboard_welcome, store.getName()));

        int[] cardIds = {R.id.cardMenu, R.id.cardCart, R.id.cardOrders,
                R.id.cardOffers, R.id.cardFavourites, R.id.cardProfile};
        int[] labelIds = {R.string.module_menu, R.string.module_cart, R.string.module_orders,
                R.string.module_offers, R.string.module_favourites, R.string.module_profile};

        for (int i = 0; i < cardIds.length; i++) {
            final int labelId = labelIds[i];
            final boolean isProfile = cardIds[i] == R.id.cardProfile;
            findViewById(cardIds[i]).setOnClickListener(v -> {
                if (isProfile) {
                    startActivity(new Intent(DashboardActivity.this, ProfileActivity.class));
                } else {
                    Toast.makeText(DashboardActivity.this,
                            getString(R.string.coming_soon, getString(labelId)),
                            Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
