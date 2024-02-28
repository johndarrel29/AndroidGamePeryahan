package com.example.buzzwire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    ImageButton settingsButton, playButton, returnButton, scoresButton;
    SoundManager soundManager = SoundManager.getSoundManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize media player and play bg music
        soundManager.setBackgroundMusic(MediaPlayer.create(this, R.raw.background_music));
        soundManager.setButtonSound(MediaPlayer.create(this, R.raw.button_press_sound));
        soundManager.playBackgroundMusic();

        playButton = findViewById(R.id.imageButtonPlay);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundManager.playButtonSound();
                playButton.setImageResource(R.drawable.played_btn);
                Intent intent = new Intent(getApplicationContext(), PlayActivity.class);
                startActivity(intent);
            }
        });

        scoresButton = findViewById(R.id.imageButtonScores);
        scoresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundManager.playButtonSound();
                scoresButton.setImageResource(R.drawable.scored_btn);
                Intent intent = new Intent(getApplicationContext(), ScoresActivity.class);
                startActivity(intent);
            }
        });

        returnButton = findViewById(R.id.imageButtonReturn);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundManager.playButtonSound();
                // Insert intent here
            }
        });

        settingsButton = findViewById(R.id.imageButtonSettings);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundManager.playButtonSound();
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(intent);
            }
        });
    }


    // For setting onResume and onPause behaviors of components (music, button)
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
        playButton.setImageResource(R.drawable.play_btn);
        scoresButton.setImageResource(R.drawable.scores_btn);
    }
}