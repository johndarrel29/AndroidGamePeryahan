package com.example.perya;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.perya.databinding.GameselectorpageBinding;

public class gameselectorpage extends AppCompatActivity {

   GameselectorpageBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = GameselectorpageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new GamesFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.games) {
                replaceFragment(new GamesFragment());
            } else if (item.getItemId() == R.id.credits) {
                replaceFragment(new CreditsFragment());
            } else if (item.getItemId() == R.id.about) {
                replaceFragment(new AboutFragment());
            }


            return true;
        });

    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.gameselectorframe,fragment);
        fragmentTransaction.commit();
    }
}
