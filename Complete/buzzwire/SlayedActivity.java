package com.example.buzzwire;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SlayedActivity extends AppCompatActivity {
    ImageButton playAgainButton, homeButton;
    TextView scoreTextView;
    Indicator indicator = Indicator.getIndicator();
    String flag = indicator.getFlag();
    User user = User.getUser();
    SoundManager soundManager = SoundManager.getSoundManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getAttributes().windowAnimations = R.style.transitionNone;
        setContentView(R.layout.activity_slayed);

        scoreTextView = findViewById(R.id.textViewScore);

        soundManager.playWinSound();

        switch (flag) {
            case "Easy":
                scoreTextView.setText(user.getScoreEasy());
                break;
            case "Medium":
                scoreTextView.setText(user.getScoreMedium());
                break;
            case "Hard":
                scoreTextView.setText(user.getScoreHard());
                break;
        }

        playAgainButton = findViewById(R.id.imageButtonPlayAgain);
        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundManager.playButtonSound();
                redirect();
            }
        });

        homeButton = findViewById(R.id.imageButtonHome);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundManager.playButtonSound();
                getOnBackPressedDispatcher().onBackPressed();
            }
        });
    }

    // Game will restart if back button is pressed
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        getOnBackPressedDispatcher().onBackPressed();
    }

    public void redirect() {
        switch (flag) {
            case "Easy": {
                Intent intent = new Intent(getApplicationContext(), EasyActivity.class);
                startActivity(intent);
                finish();
                break;
            }
            case "Medium": {
                Intent intent = new Intent(getApplicationContext(), MediumActivity.class);
                startActivity(intent);
                finish();
                break;
            }
            case "Hard": {
                Intent intent = new Intent(getApplicationContext(), HardActivity.class);
                startActivity(intent);
                finish();
                break;
            }
        }
    }
}
