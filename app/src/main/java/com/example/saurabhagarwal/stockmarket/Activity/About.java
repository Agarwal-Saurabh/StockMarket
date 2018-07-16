package com.example.saurabhagarwal.stockmarket.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.saurabhagarwal.stockmarket.R;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("About Us");
    }
}
