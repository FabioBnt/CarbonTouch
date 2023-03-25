package com.example.carbontouch;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

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
        return "Gas car";
    }

    @Override
    public String getAnswer4() {
        return "electric car";
    }
}

