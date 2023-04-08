package com.example.carbontouch;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public abstract class CardFragment extends Fragment {
    private ImageView imageView;
    private TextView questionTextView;
    private RadioGroup radioGroup;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private RadioButton radioButton4;

    public CardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.card_layout, container, false);

        imageView = view.findViewById(R.id.imageView);
        questionTextView = view.findViewById(R.id.questionTextView);
        radioGroup = view.findViewById(R.id.answerRadioGroup);
        radioButton1 = view.findViewById(R.id.answer1RadioButton);
        radioButton2 = view.findViewById(R.id.answer2RadioButton);
        radioButton3 = view.findViewById(R.id.answer3RadioButton);
        radioButton4 = view.findViewById(R.id.answer4RadioButton);

        imageView.setImageResource(R.drawable.ic_launcher_background);
        // Set the question text
        questionTextView.setText(getQuestion());
        // Set the radio button options
        radioButton1.setText(getAnswer1());
        radioButton2.setText(getAnswer2());
        radioButton3.setText(getAnswer3());
        radioButton4.setText(getAnswer4());
        return view;
    }

    public int getAnswer() {
        return radioGroup.getCheckedRadioButtonId();
    }

    public abstract String getQuestion();
    public abstract String getAnswer1();
    public abstract String getAnswer2();
    public abstract String getAnswer3();
    public abstract String getAnswer4();

    public double getCarbonIntensity() {
        // calculate the carbon intensity
        if (getAnswer() == R.id.answer1RadioButton) {
            return 1.0;
        } else if (getAnswer() == R.id.answer2RadioButton) {
            return 5.0;
        } else if (getAnswer() == R.id.answer3RadioButton) {
            return 10.0;
        } else if (getAnswer() == R.id.answer4RadioButton) {
            return 25.0;
        } else {
            return 0.0;
        }
    }
}
