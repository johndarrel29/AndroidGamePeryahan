package com.example.buzzwire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.content.Intent;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private
    ImageButton btnSettings;
    ImageButton btnPlay;
    ImageButton btnReturn;
    ImageButton btnScores;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSettings = (ImageButton) findViewById(R.id.imageSettings);
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Settings.class);
                startActivity(intent);
            }
        });
        btnPlay = (ImageButton) findViewById(R.id.imageViewPlay);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        btnReturn = (ImageButton) findViewById(R.id.imageReturn);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        btnScores = (ImageButton) findViewById(R.id.imageScores);
        btnScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Scores.class);
                startActivity(intent);
            }
        });

    }
}