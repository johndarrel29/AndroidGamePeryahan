package com.example.buzzwire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class ScoresActivity extends AppCompatActivity {
    ImageButton backButton;
    TextView scoreEasyTextView, scoreMediumTextView, scoreHardTextView;
    SoundManager soundManager = SoundManager.getSoundManager();
    User user = User.getUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        scoreEasyTextView = findViewById(R.id.textViewScoreEasy);
        scoreEasyTextView.setText(user.getBestScoreEasy());

        scoreMediumTextView = findViewById(R.id.textViewScoreMedium);
        scoreMediumTextView.setText(user.getBestScoreMedium());

        scoreHardTextView = findViewById(R.id.textViewScoreHard);
        scoreHardTextView.setText(user.getBestScoreHard());

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
    }
}