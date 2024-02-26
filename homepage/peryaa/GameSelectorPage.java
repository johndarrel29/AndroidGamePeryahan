package com.example.peryaa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.style.BulletSpan;
import android.view.View;
import android.widget.Button;

import com.example.peryaa.databinding.ActivityGameSelectorPageBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class GameSelectorPage extends AppCompatActivity{

    ViewPager2 viewPager2;
    ArrayList<ViewPagerItem> viewPagerItemArrayList;

    int cont;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_selector_page);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.bottom_games);

        bottomNavigationView.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.bottom_games){
                return true;
            } else if (item.getItemId() == R.id.bottom_about){
                startActivity(new Intent(GameSelectorPage.this, AboutPage.class));
            } else if (item.getItemId() == R.id.bottom_credits) {
                startActivity(new Intent(GameSelectorPage.this, CreditsPage.class));
            }

            return true;
        });

        viewPager2 = findViewById(R.id.viewpager);
        int[] images = {R.drawable.download,R.drawable.buzz,R.drawable.personality};
        String[] title = {"Color Game", "Buzz Wire", "Personality Test"};

        viewPagerItemArrayList = new ArrayList<>();

        for (int i = 0; i < images.length; i++){

            ViewPagerItem viewPagerItem = new ViewPagerItem(images[i], title[i]);
            viewPagerItemArrayList.add(viewPagerItem);

        }

        VPAdapter vpAdapter = new VPAdapter(viewPagerItemArrayList);

        viewPager2.setAdapter(vpAdapter);

        viewPager2.setClipToPadding(false);

        viewPager2.setClipChildren(false);

        viewPager2.setOffscreenPageLimit(2);

        viewPager2.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);


        Button playbutton = findViewById(R.id.playbutton);

        playbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (cont){
                    case 1: startActivity(new Intent(GameSelectorPage.this, ColorGame.class)); break;
                    case 2: startActivity(new Intent(GameSelectorPage.this, BuzzWire.class)); break;
                    case 3: startActivity(new Intent(GameSelectorPage.this, PersonalityTest.class)); break;
                }
            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position){
                    case 0: cont = 1; break;
                    case 1: cont = 2; break;
                    case 2: cont = 3; break;
                    default: cont = 0; break;
                }
            }
        });

    }
}