package com.example.buzzwire;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {
    ImageButton musicButton, soundButton, backButton;
    SoundManager soundManager = SoundManager.getSoundManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Music button
        musicButton = findViewById(R.id.imageButtonMusic);

        if (soundManager.isMusicOn) {
            musicButton.setImageResource(R.drawable.on_btn);
        }
        else {
            musicButton.setImageResource(R.drawable.off_btn);
        }

        musicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundManager.playButtonSound();
                if (soundManager.isMusicOn) {
                    soundManager.turnOffMusic();
                    musicButton.setImageResource(R.drawable.off_btn);
                }
                else {
                    soundManager.turnOnMusic();
                    soundManager.playBackgroundMusic();
                    musicButton.setImageResource(R.drawable.on_btn);
                }
            }
        });

        // Sound button
        soundButton = findViewById(R.id.imageButtonSound);

        if (soundManager.isSoundOn) {
            soundButton.setImageResource(R.drawable.on_btn);
        }
        else {
            soundButton.setImageResource(R.drawable.off_btn);
        }

        soundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundManager.playButtonSound();
                if (soundManager.isSoundOn) {
                    soundManager.turnOffSound();
                    soundButton.setImageResource(R.drawable.off_btn);
                }
                else {
                    soundManager.turnOnSound();
                    soundManager.playButtonSound();
                    soundButton.setImageResource(R.drawable.on_btn);
                }
            }
        });

        // Back button
        backButton = findViewById(R.id.imageButtonBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
    }
}
