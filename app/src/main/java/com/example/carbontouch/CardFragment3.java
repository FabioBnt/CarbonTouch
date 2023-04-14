package com.example.carbontouch;

public class CardFragment3 extends CardFragment {


    public CardFragment3() {
        // Required empty public constructor
    }

    public String getQuestion() {
        return getResources().getString(R.string.question_3);
    }

    public String getAnswer1() {
        return getResources().getString(R.string.a1_q3);
    }

    public String getAnswer2() {
        return getResources().getString(R.string.a2_q3);
    }

    public String getAnswer3() {
        return getResources().getString(R.string.a3_q3);
    }

    public String getAnswer4() {
        return getResources().getString(R.string.a4_q3);
    }

    @Override
    public int getImageView() {
        return R.drawable.devices;
    }
}
