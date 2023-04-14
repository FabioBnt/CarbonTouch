package com.example.carbontouch;

public class CardFragment1 extends CardFragment {

    public CardFragment1() {
        // Required empty public constructor
    }
    @Override
    public String getQuestion() {
        return getResources().getString(R.string.question_1);
    }

    //TODO : externalize strings
    @Override
    public String getAnswer1() {
        return getResources().getString(R.string.a1_q1);
    }

    @Override
    public String getAnswer2() {
        return getResources().getString(R.string.a2_q1);
    }

    @Override
    public String getAnswer3() {
        return getResources().getString(R.string.a3_q1);
    }

    @Override
    public String getAnswer4() {
        return getResources().getString(R.string.a4_q1);
    }

    @Override
    public int getImageView(){
        return R.drawable.methods;
    }

}

