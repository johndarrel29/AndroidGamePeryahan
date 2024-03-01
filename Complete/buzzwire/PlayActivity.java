package com.example.buzzwire;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class PlayActivity extends AppCompatActivity {
    ImageButton backButton, easyButton, mediumButton, hardButton;
    SoundManager soundManager = SoundManager.getSoundManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        easyButton = (ImageButton) findViewById(R.id.imageButtonEasy);
        easyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundManager.playButtonSound();
                easyButton.setImageResource(R.drawable.easy_btn_p);
                Intent intent = new Intent(getApplicationContext(), EasyActivity.class);
                startActivity(intent);
            }
        });

        mediumButton = (ImageButton) findViewById(R.id.imageButtonMedium);
        mediumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundManager.playButtonSound();
                mediumButton.setImageResource(R.drawable.medium_btn_p);
                Intent intent = new Intent(getApplicationContext(), MediumActivity.class);
                startActivity(intent);
            }
        });

        hardButton = (ImageButton) findViewById(R.id.imageButtonHard);
        hardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundManager.playButtonSound();
                hardButton.setImageResource(R.drawable.hard_btn_p);
                Intent intent = new Intent(getApplicationContext(), HardActivity.class);
                startActivity(intent);
            }
        });

        backButton = findViewById(R.id.imageButtonBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundManager.playButtonSound();
                backButton.setImageResource(R.drawable.backed_btn);
                getOnBackPressedDispatcher().onBackPressed();
            }
        });
    }


    @Override
    protected void onPause() {
        super.onPause();
        if(soundManager.isMusicOn) {
            if(soundManager.backgroundMusicPlayer.isPlaying()) {
                soundManager.backgroundMusicPlayer.pause();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(soundManager.isMusicOn) {
            if(!soundManager.backgroundMusicPlayer.isPlaying()) {
                soundManager.turnOnMusic();
                soundManager.playBackgroundMusic();
            }
        }

        // Set buttons to its default background
        easyButton.setImageResource(R.drawable.easy_btn);
        mediumButton.setImageResource(R.drawable.medium_btn);
        hardButton.setImageResource(R.drawable.hard_btn);
    }
}
