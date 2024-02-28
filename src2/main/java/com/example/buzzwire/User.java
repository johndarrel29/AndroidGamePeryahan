package com.example.buzzwire;

public class User {
    static User user;
    String scoreEasy = "00:00";
    String scoreMedium = "00:00";
    String scoreHard = "00:00";
    String bestScoreEasy = "00:00";
    String bestScoreMedium = "00:00";
    String bestScoreHard = "00:00";

    public static User getUser() {
        if (user == null) {
            user = new User();
        }
        return user;
    }

    public String getScoreEasy() {
        return scoreEasy;
    }

    public void setScoreEasy(String scoreEasy) {
        this.scoreEasy = scoreEasy;
    }

    public String getScoreMedium() {
        return scoreMedium;
    }

    public void setScoreMedium(String scoreMedium) {
        this.scoreMedium = scoreMedium;
    }

    public String getScoreHard() {
        return scoreHard;
    }

    public void setScoreHard(String scoreHard) {
        this.scoreHard = scoreHard;
    }

    public String getBestScoreEasy() {
        return bestScoreEasy;
    }

    public void setBestScoreEasy(String bestScoreEasy) {
        this.bestScoreEasy = bestScoreEasy;
    }

    public String getBestScoreMedium() {
        return bestScoreMedium;
    }

    public void setBestScoreMedium(String bestScoreMedium) {
        this.bestScoreMedium = bestScoreMedium;
    }

    public String getBestScoreHard() {
        return bestScoreHard;
    }

    public void setBestScoreHard(String bestScoreHard) {
        this.bestScoreHard = bestScoreHard;
    }
}
