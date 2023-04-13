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

public class CardFragment2 extends CardFragment {


        public CardFragment2() {
            // Required empty public constructor
        }

    @Override
    public String getQuestion() {
        return getResources().getString(R.string.question_2);
    }

    //TODO : externalize strings

    @Override
    public String getAnswer1() {
        return "1";
    }

    @Override
    public String getAnswer2() {
        return "2";
    }

    @Override
    public String getAnswer3() {
        return "3";
    }

    @Override
    public String getAnswer4() {
        return "4 or more";
    }

    @Override
    public int getImageView() {
        return R.drawable.household;
    }
}
