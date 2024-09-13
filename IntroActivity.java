package com.example.udmenglishmaster;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AppCompatActivity {

    //firebase auth
    private FirebaseAuth firebaseAuth;

    private ViewPager2 screenPager;
    private IntroViewPagerAdapter introViewPagerAdapter;
    private TabLayout tabIndicator;
    Button btnNext;
    int position = 0;
    Button btnGetStarted;
    Animation btnAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        //init firebase auth
        firebaseAuth = FirebaseAuth.getInstance();



        // Initialize views
        btnNext = findViewById(R.id.btn_next);
        btnGetStarted = findViewById(R.id.btn_get_started);
        screenPager = findViewById(R.id.screen_viewpager);
        btnAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_animation);

        // Initialize ViewPager2
        tabIndicator = findViewById(R.id.tabLayout);

        // Fill list screen
        List<ScreenItem> mList = new ArrayList<>();
        mList.add(new ScreenItem("English Language", "Learn and master the English language with comprehensive lessons and exercises.", R.drawable.dic2));
        mList.add(new ScreenItem("Quizzes", "Test your knowledge with a variety of quizzes designed to reinforce your learning.", R.drawable.quizz1));
        mList.add(new ScreenItem("Forum", "Join the community! Discuss, ask questions, and share your knowledge in the forum.", R.drawable.chat));

        // Setup ViewPager2
        introViewPagerAdapter = new IntroViewPagerAdapter(this, mList);
        screenPager.setAdapter(introViewPagerAdapter);

        // Setup TabLayout with ViewPager
        new TabLayoutMediator(tabIndicator, screenPager, (tab, position1) -> {
            // Set the tab text to empty to hide the text
            tab.setText("");
        }).attach();

        // Next button click listener
        btnNext.setOnClickListener(v -> {
            position = screenPager.getCurrentItem();
            if (position < mList.size()) {
                position++;
                screenPager.setCurrentItem(position);
            }

            if (position == mList.size() - 1) { // when we reach to the last screen
                loadLastScreen();
            }
        });

        // TabLayout change listener
        tabIndicator.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == mList.size() - 1) {
                    loadLastScreen();
                }
            }


            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }

            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });

        // Get started button click listener
        btnGetStarted.setOnClickListener(v -> {
            // Open LoginActivity
            Intent loginActivity = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(loginActivity);

            // Save preference data

            finish();
        });
    }

    private void loadLastScreen() {
        btnNext.setVisibility(View.INVISIBLE);
        btnGetStarted.setVisibility(View.VISIBLE);
        tabIndicator.setVisibility(View.INVISIBLE);

        // Add animation to the Get Started button
        btnGetStarted.setAnimation(btnAnim);
    }


}
