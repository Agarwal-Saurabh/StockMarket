package com.example.saurabhagarwal.stockmarket.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.saurabhagarwal.stockmarket.R;


public class Contact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Contact Us");
    }
}
