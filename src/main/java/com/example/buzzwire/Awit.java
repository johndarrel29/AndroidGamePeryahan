package com.example.buzzwire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Awit extends AppCompatActivity {

    ImageButton btnHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awit);

        btnHome = (ImageButton) findViewById(R.id.imageAwHomeBtn);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Awit.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}