package com.example.carbontouch;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    private static final int NUM_PAGES = 3; // Number of questions/cards

    private ViewPager2 viewPager;
    private Button buttonPrev;
    private Button buttonNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        // Initialize views
        viewPager = findViewById(R.id.view_pager);
        buttonPrev = findViewById(R.id.button_prev);
        buttonNext = findViewById(R.id.button_next);

        // Set up ViewPager adapter
        TestPagerAdapter adapter = new TestPagerAdapter(getSupportFragmentManager(), getLifecycle());
        viewPager.setAdapter(adapter);

        // Set up button click listeners
        buttonPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewPager.getCurrentItem() > 0) {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
                    // set the button text to "Next"
                    buttonNext.setText("Next");
                }else {
                    // disable the previous button
                    buttonPrev.setEnabled(false);
                }
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewPager.getCurrentItem() < NUM_PAGES - 1) {int selectedAnswer = -1;
                    // get the selected answer
                    selectedAnswer = adapter.getAnswer(viewPager.getCurrentItem());
                    if (selectedAnswer == -1) {
                        Toast.makeText(TestActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    // enable the previous button
                    buttonPrev.setEnabled(true);
                }
                else if(viewPager.getCurrentItem() == NUM_PAGES - 2){
                    // change the button text to "Finish"
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    buttonNext.setText("Finish");
                }else {
                    //Todo: calculate the results
                    // return the results
                    //1 - calculate the results
                    int result = 0;
                    for (int i = 0; i < NUM_PAGES; i++) {
                        int selectedAnswer = adapter.getAnswer(i);
                        if (selectedAnswer == -1) {
                            Toast.makeText(TestActivity.this, "Please answer all questions before submitting", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        result += selectedAnswer;
                    }
                    //2 - return the results
                    Intent intent = new Intent();
                    intent.putExtra("result", result);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }

    // Adapter to display the cards
    private static class TestPagerAdapter extends FragmentStateAdapter {
        private List<CardFragment> fragments;

        public TestPagerAdapter(FragmentManager fragmentManager, Lifecycle lifecycle) {
            super(fragmentManager, lifecycle);
            fragments = new ArrayList<>();
            fragments.add(new CardFragment1());
            fragments.add(new CardFragment2());
            fragments.add(new CardFragment3());
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return fragments.get(position);
        }

        @Override
        public int getItemCount() {
            return fragments.size();
        }

        public int getAnswer(int position) {
            CardFragment fragment = fragments.get(position);
            if (fragment != null) {
                return fragment.getAnswer();
            }
            return -1;
        }
    }


}
