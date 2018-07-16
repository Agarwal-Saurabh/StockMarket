package com.example.saurabhagarwal.stockmarket.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.saurabhagarwal.stockmarket.R;

public class Flexible extends AppCompatActivity {

    double gain_per_day, gain_per_stock;
    int n1, n2, n3, n4, n5;
    TextView t1,t2,t3,t4,t5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flexible);
        init();
        method();
    }

    private void init() {
        t1 = (TextView) findViewById(R.id.n1);
        t2 = (TextView) findViewById(R.id.n2);
        t3 = (TextView) findViewById(R.id.n3);
        t4 = (TextView) findViewById(R.id.n4);
        t5 = (TextView) findViewById(R.id.n5);

    }

    private void method() {

        gain_per_day =  Math.ceil(Inveestment.gain / Inveestment.days);
        gain_per_stock = Math.ceil(gain_per_day / 5.0);

        n1 = (int) Math.ceil(gain_per_stock / 2.6);
        n2 = (int) Math.ceil(gain_per_stock / 0.6);
        n3 = (int) Math.ceil(gain_per_stock / 5.94);
        n4 = (int) Math.ceil(gain_per_stock / 5.31);
        n5 = (int) Math.ceil(gain_per_stock / 5.70);

        t1.setText(""+n1+" shares");
        t2.setText(""+n2+" shares");
        t3.setText(""+n3+" shares");
        t4.setText(""+n4+" shares");
        t5.setText(""+n5+" shares");

    }

}
