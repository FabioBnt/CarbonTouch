package com.example.carbontouch;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class TestInstructionsActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private Button skipButton;
    private Button startButton;
    public static final int NUM_CARDS = 3;

    private int mCurrentCardIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_instructions);

        viewPager = findViewById(R.id.view_pager);
        skipButton = findViewById(R.id.skip_button);
        startButton = findViewById(R.id.start_button);
        startButton.setText("Next");

        List<View> cardViews = new ArrayList<>();
        cardViews.add(getLayoutInflater().inflate(R.layout.card_view_1, null));
        cardViews.add(getLayoutInflater().inflate(R.layout.card_view_2, null));
        cardViews.add(getLayoutInflater().inflate(R.layout.card_view_3, null));

        InstructionsPagerAdapter adapter = new InstructionsPagerAdapter(cardViews);
        viewPager.setAdapter(adapter);
        // on page change
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mCurrentCardIndex = position;
                // If on the final card, set button text to "Start"
                if (position == NUM_CARDS - 1) {
                    startButton.setText("Start");
                } else {
                    startButton.setText("Next");
                }
                // if second card search for button and check if username is not null
                if (position == 1) {
                    Button button = findViewById(R.id.login_button);
                    // if username exists then hide the button
                    if (getIntent().getStringExtra("username") != null) {
                        button.setVisibility(View.GONE);
                    }else
                        button.setVisibility(View.VISIBLE);
                        button.setText("return to login");
                        // event listener for the button
                        button.setOnClickListener(v -> {
                            // close this activity
                            finish();
                        });
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // go to the last card
                viewPager.setCurrentItem(NUM_CARDS - 1);
                // If on the final card, set button text to "Start"
                startButton.setText("Start");
            }
        });

        startButton.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button startButton = findViewById(R.id.start_button);
                    if (mCurrentCardIndex < NUM_CARDS - 2) {
                        // If not on the final card, increment the index and set button text to "Next"
                        mCurrentCardIndex++;
                        startButton.setText("Next");
                        // go to the next card
                        viewPager.setCurrentItem(mCurrentCardIndex);
                    }else if (mCurrentCardIndex == NUM_CARDS - 1){
                        // open the activity "TestActivity" and close this activity
                        Intent intent = new Intent(TestInstructionsActivity.this, TestActivity.class);
                        // if username exists then pass it to the next activity
                        if (getIntent().getStringExtra("username") != null) {
                            intent.putExtra("username", getIntent().getStringExtra("username"));
                        }
                        startActivity(intent);
                        finish();
                    } else {
                        // go to the last card
                        viewPager.setCurrentItem(NUM_CARDS - 1);
                        // If on the final card, set button text to "Start"
                        startButton.setText("Start");
                    }
                }
            }
        );
    }
}


