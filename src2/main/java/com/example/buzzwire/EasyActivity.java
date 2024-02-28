package com.example.buzzwire;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class EasyActivity extends AppCompatActivity implements View.OnTouchListener {
    ImageButton returnButton, pauseButton;
    ImageView loopImageView, finishBoxImageView;
    ImageView[] hitBoxImageView;
    TextView timerTextView;

    boolean isTimerPaused = false;
    int prevX, prevY;
    int screenWidth, screenHeight;
    TimerManager timerManager;
    User user = User.getUser();
    Indicator indicator = Indicator.getIndicator();
    SoundManager soundManager = SoundManager.getSoundManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy);

        // Get screen dimensions
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenWidth = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;

        // Timer
        timerTextView = findViewById(R.id.textViewTimer); // Get reference to timer TextView
        timerManager = new TimerManager(timerTextView);
        timerManager.startTimer();

        returnButton = findViewById(R.id.imageButtonReturn);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundManager.playButtonSound();
                getOnBackPressedDispatcher().onBackPressed();
            }
        });

        // Pause or resume button
        pauseButton = findViewById(R.id.imageButtonPause);
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundManager.playButtonSound();
                if (isTimerPaused) {
                    timerManager.resumeTimer();
                    isTimerPaused = false;
                    pauseButton.setImageResource(R.drawable.pause_icon_btn);
                } else {
                    timerManager.pauseTimer();
                    isTimerPaused = true;
                    pauseButton.setImageResource(R.drawable.play_icon_btn);
                }
            }
        });

        // Hit and finish box
        hitBoxImageView = new ImageView[46];
        for (int i = 0; i < 46; i++) {
            int resId = getResources().getIdentifier("imageView" + (i + 1), "id", getPackageName());
            hitBoxImageView[i] = findViewById(resId);
        }

        finishBoxImageView = findViewById(R.id.finishBox);

        // Set on touch listener to drag the loop
        loopImageView = findViewById(R.id.imageViewLoop);
        loopImageView.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (isTimerPaused) {
            return false;
        }
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();

        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                ConstraintLayout.LayoutParams lParams = (ConstraintLayout.LayoutParams) v.getLayoutParams();
                prevX = x - lParams.leftMargin;
                prevY = y - lParams.topMargin;
                break;
            case MotionEvent.ACTION_MOVE:
                // Calculate the new position of the image
                int newX = x - prevX;
                int newY = y - prevY;

                // Limit the image to stay within the screen bounds
                newX = Math.max(0, Math.min(screenWidth - v.getWidth(), newX));
                newY = Math.max(0, Math.min(screenHeight - v.getHeight(), newY));

                // Update the layout parameters with the new position
                ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) v.getLayoutParams();
                layoutParams.leftMargin = newX;
                layoutParams.topMargin = newY;

                v.setLayoutParams(layoutParams);

                // Check if the metal loop intersects with the finish box
                if (isIntersecting(loopImageView, finishBoxImageView)) {
                    timerManager.stopTimer();
                    user.setScoreEasy(timerManager.getTime());
                    indicator.setFlag("Easy");

                    compareScore(user.getScoreEasy(), user.getBestScoreEasy());

                    Intent intent = new Intent(getApplicationContext(), SlayedActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                } else {
                    // Check if the metal loop intersects with any of the hit box
                    for (ImageView imageView : hitBoxImageView) {
                        if (isIntersecting(loopImageView, imageView)) {
                            timerManager.stopTimer();
                            user.setScoreEasy(timerManager.getTime());
                            indicator.setFlag("Easy");

                            Intent intent = new Intent(getApplicationContext(), AwitActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();
                        }
                    }
                }
                break;
        }
        return true;
    }

    // Checks if the metal loop touches the hit box
    public boolean isIntersecting(ImageView wireLoopImageView, ImageView hitBoxImageView) {
        Rect rect1 = new Rect();
        wireLoopImageView.getHitRect(rect1);
        Rect rect2 = new Rect();
        hitBoxImageView.getHitRect(rect2);
        return rect1.intersect(rect2);
    }

    // Compare score to set the new best score
    public void compareScore(String currentScore, String bestScore) {
        int score1 = timeStringToSeconds(currentScore);
        int score2 = timeStringToSeconds(bestScore);

        if (bestScore.equals("00:00")) {
            user.setBestScoreEasy(currentScore);
        } else if (score1 < score2) {
            user.setBestScoreEasy(currentScore);
        }
    }

    public int timeStringToSeconds(String timeString) {
        String[] timeParts = timeString.split(":");
        int minutes = Integer.parseInt(timeParts[0]);
        int seconds = Integer.parseInt(timeParts[1]);

        return minutes * 60 + seconds;
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
