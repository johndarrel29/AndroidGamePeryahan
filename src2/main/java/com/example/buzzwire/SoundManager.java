package com.example.buzzwire;

import android.media.MediaPlayer;

public class SoundManager {
    static SoundManager soundManager = new SoundManager();
    MediaPlayer backgroundMusicPlayer, buttonSoundPlayer;
    Boolean isMusicOn = true;
    Boolean isSoundOn = true;

    public static SoundManager getSoundManager() {
        return soundManager;
    }

    public void setBackgroundMusic(MediaPlayer backgroundMusic) {
        this.backgroundMusicPlayer = backgroundMusic;
    }

    public void setButtonSound(MediaPlayer buttonSound) {
        this.buttonSoundPlayer = buttonSound;
    }

    public void playBackgroundMusic() {
        if (isMusicOn) {
            backgroundMusicPlayer.start();
            backgroundMusicPlayer.setLooping(true);
        }
    }

    public void playButtonSound() {
        if (isSoundOn) {
            buttonSoundPlayer.start();
        }
    }

    public void turnOnMusic() {
        isMusicOn = true;
    }

    public void turnOffMusic() {
        backgroundMusicPlayer.pause();
        isMusicOn = false;
    }

    public void turnOnSound() {
        isSoundOn = true;
    }

    public void turnOffSound() {
        buttonSoundPlayer.pause();
        isSoundOn = false;
    }
}
