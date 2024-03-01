package com.example.buzzwire;

public class Indicator {
    static Indicator indicator;
    String flag;

    public static Indicator getIndicator() {
        if (indicator == null) {
            indicator = new Indicator();
        }
        return indicator;
    }
    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
