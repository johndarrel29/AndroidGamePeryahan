package com.example.peryaa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.peryaa.databinding.ActivityCreditsPageBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CreditsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits_page);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.bottom_credits);

        bottomNavigationView.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.bottom_credits){
                return true;
            } else if (item.getItemId() == R.id.bottom_about){
                startActivity(new Intent(CreditsPage.this, AboutPage.class));
            } else if (item.getItemId() == R.id.bottom_games) {
                startActivity(new Intent(CreditsPage.this, GameSelectorPage.class));
            }

            return true;
        });
    }
}