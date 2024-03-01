package com.example.perya;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;


public class GamesFragment extends Fragment implements RecyclerViewInterface {

    View myFragment;
    RecyclerView viewPager2;
    ArrayList<ViewPagerItem> viewPagerItemArrayList;

    VPAdapter vpAdapter;

    Activity context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = getActivity();
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_games, container, false);


        viewPager2 = root.findViewById(R.id.viewpager);
        viewPager2.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));

        viewPagerItemArrayList = new ArrayList<>();

        viewPagerItemArrayList.add(new ViewPagerItem(R.drawable.download));
        viewPagerItemArrayList.add(new ViewPagerItem(R.drawable.buzz));
        viewPagerItemArrayList.add(new ViewPagerItem(R.drawable.personality));

        vpAdapter = new VPAdapter(getActivity(),viewPagerItemArrayList, this);
        viewPager2.setAdapter(vpAdapter);

        LinearSnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(viewPager2);

        viewPager2.setOnFlingListener(snapHelper);



        return root;
    }

    @Override
    public void onItemClick(int position) {
        switch (position){
            case 0: startActivity(new Intent(context, ColorGame.class)); break;
            case 1: startActivity(new Intent(context, BuzzWire.class)); break;
            case 2: startActivity(new Intent(context, PersonalityTest.class)); break;
        }
    }
}