package com.github.githubdev.intro;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.github.githubdev.main.MainActivity;
import com.github.githubdev.R;
import com.github.githubdev.searchusername.SearchUsername;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AppCompatActivity {

    private ViewPager screenPager;
    IntroViewPagerAdapter introViewPagerAdapter;
    TabLayout tabIndicator;
    Button btnNext;
    int position = 0;
    Button btnGetStarted;
    Animation btnAnim;
    Animation imageAnimation;
    Animation titleAnimation;
    Animation descAnimation;
    Boolean lastScreenLoad = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Full Screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Jika restorePrefData bernilai true (intro telah dijalankan)
        if (restorePrefData()){
            Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(mainActivity);
            finish();
        }
        setContentView(R.layout.activity_intro);
        //Get From Layout
        btnNext = findViewById(R.id.btn_next);
        btnGetStarted = findViewById(R.id.btn_get_started);
        tabIndicator = findViewById(R.id.tab_indicator);
        btnAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.button_animation);
        imageAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.image_intro);
        titleAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.title_intro);
        descAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.description_intro);

        //Fill list screen
        final List<ScreenIntro> mList = new ArrayList<>();
        mList.add(new ScreenIntro("Welcome to GitHub-Dev","One of the most rich people and 100% free", R.drawable.intro1, imageAnimation, titleAnimation, descAnimation));
        mList.add(new ScreenIntro("Repositories and Users","Obtain repository and user", R.drawable.intro2, imageAnimation, titleAnimation, descAnimation));
        mList.add(new ScreenIntro("Enjoy GitHub-Dev","Thanks for change this application", R.drawable.intro3, imageAnimation, titleAnimation, descAnimation));

        //Setup Viewpager
        screenPager = findViewById(R.id.screen_viewpager);
        introViewPagerAdapter = new IntroViewPagerAdapter(this, mList);
        screenPager.setAdapter(introViewPagerAdapter);


        //setup tablayout
        tabIndicator.setupWithViewPager(screenPager);

        //Btn next event
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            position = screenPager.getCurrentItem();
            if (position < mList.size()){
                position++;
                screenPager.setCurrentItem(position);
            }
            if (position == mList.size()-1){
                lastScreenLoad = true;
                loadLastScreen();
            }
            }
        });

        //tablayout add change listener
        tabIndicator.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if (tab.getPosition() == mList.size()-1){
                    lastScreenLoad = true;
                    loadLastScreen();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        //get started button click listener
        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchUsername.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private boolean restorePrefData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
        Boolean isIntroActivityOpenedBefore = pref.getBoolean("isIntroOpen",false);
        return isIntroActivityOpenedBefore;

    }

    public void savePrefsData(boolean save, Context context) {
        SharedPreferences pref = context.getSharedPreferences("myPrefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isIntroOpen",save);
        editor.commit();
    }

    private void loadLastScreen(){
        btnNext.setVisibility(View.INVISIBLE);
        btnGetStarted.setVisibility(View.VISIBLE);
        tabIndicator.setVisibility(View.INVISIBLE);
        if(lastScreenLoad == false) {
            btnGetStarted.setAnimation(btnAnim);
        }
    }
}
