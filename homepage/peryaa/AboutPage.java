package com.example.peryaa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.peryaa.databinding.ActivityAboutPageBinding;
import com.example.peryaa.databinding.ActivityGameSelectorPageBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AboutPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_page);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.bottom_about);

        bottomNavigationView.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.bottom_about){
                return true;
            } else if (item.getItemId() == R.id.bottom_games){
                startActivity(new Intent(AboutPage.this, GameSelectorPage.class));
            } else if (item.getItemId() == R.id.bottom_credits) {
                startActivity(new Intent(AboutPage.this, CreditsPage.class));
            }

            return true;
        });
    }
}