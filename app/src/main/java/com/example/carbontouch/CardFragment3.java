package com.example.carbontouch;

import android.os.Bundle;

public class CardFragment3 extends CardFragment {

    public CardFragment3() {
        // Required empty public constructor
    }

    public String getQuestion() {
        return "How many personal devices do you have in your household?";
    }

    public String getAnswer1() {
        return "1-3";
    }

    public String getAnswer2() {
        return "4-6";
    }

    public String getAnswer3() {
        return "7-9";
    }

    public String getAnswer4() {
        return "10 or more";
    }

    @Override
    public int getImageView() {
        return R.drawable.devices;
    }
}
