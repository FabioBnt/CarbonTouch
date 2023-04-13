package com.example.carbontouch;

public class CardFragment1 extends CardFragment {

    public CardFragment1() {
        // Required empty public constructor
    }
    @Override
    public String getQuestion() {
        return "What is your preferred mode of transportation?";
    }

    @Override
    public String getAnswer1() {
        return "Walking/biking";
    }

    @Override
    public String getAnswer2() {
        return "Public transportation";
    }

    @Override
    public String getAnswer3() {
        return "Electric car";
    }

    @Override
    public String getAnswer4() {
        return "Gas car";
    }

    @Override
    public int getImageView(){
        return R.drawable.methods;
    }

}

