package com.example.pietyszukm.slidermain;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import com.viewpagerindicator.CirclePageIndicator;
import android.os.Bundle;

import java.util.ArrayList;

public class SliderActivity extends AppCompatActivity {
    private static int currentPage = 0;
    private static final Integer[] images = {
            R.drawable.first,
            R.drawable.second,
            R.drawable.third
    };
    private ArrayList<Integer> ImagesArray = new ArrayList<>();
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);
        init();
    }

    private void init(){
        for (Integer image:images) {
            ImagesArray.add(image);
        }
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new SliderAdapter(SliderActivity.this, ImagesArray));
        CirclePageIndicator indicator = (CirclePageIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(viewPager);
        final float density = getResources().getDisplayMetrics().density;

        indicator.setRadius(5 * density);
        int NUM_PAGES = images.length;

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {}

            @Override
            public void onPageScrollStateChanged(int pos) {}
        });

    }
}
