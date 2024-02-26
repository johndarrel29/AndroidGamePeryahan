package com.example.buzzwire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Play extends AppCompatActivity {

    ImageButton btnBack;
    ImageButton btnEasy;
    ImageButton btnMedium;
    ImageButton btnHard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        btnBack = (ImageButton) findViewById(R.id.imagePlayBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Play.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btnEasy = (ImageButton) findViewById(R.id.imageEasyBtn);
        btnEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Play.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btnMedium = (ImageButton) findViewById(R.id.imageMediumBtn);
        btnMedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Play.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btnHard = (ImageButton) findViewById(R.id.imageHardBtn);
        btnHard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Play.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}