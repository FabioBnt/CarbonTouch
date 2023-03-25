package com.example.carbontouch;

import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class InstructionsPagerAdapter extends PagerAdapter {

    private List<View> cardViews;

    public InstructionsPagerAdapter(List<View> cardViews) {
        this.cardViews = cardViews;
    }

    @Override
    public int getCount() {
        return cardViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View cardView = cardViews.get(position);
        container.addView(cardView);
        return cardView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View cardView = (View) object;
        container.removeView(cardView);
    }

}