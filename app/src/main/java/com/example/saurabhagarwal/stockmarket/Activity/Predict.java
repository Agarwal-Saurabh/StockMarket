package com.example.saurabhagarwal.stockmarket.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.saurabhagarwal.stockmarket.R;
import com.example.saurabhagarwal.stockmarket.helper.HttpPostAsyncTask;

import java.util.ArrayList;

public class Predict extends AppCompatActivity {

    Button btn;
    Spinner spinner;
    EditText t1, t2;
    ArrayList<String> arrayList = new ArrayList<>();
    private ArrayAdapter arrayAdapter;
    public static String date, company, close;
    public static int x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predict);
        init();
        methodListner();
        fillVaules();
        arrayAdapter = new ArrayAdapter(Predict.this, R.layout.list_item, arrayList);
        spinner.setAdapter(arrayAdapter);
    }


    private void init() {
        btn = (Button) findViewById(R.id.predict);
        t1 = (EditText) findViewById(R.id.date);
        t2 = (EditText) findViewById(R.id.close);
        spinner = (Spinner) findViewById(R.id.company);
    }

    private void methodListner() {

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String text = arrayList.get(position);
                company = "" + position;

                // Toast.makeText(Predict.this, company, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                date = t1.getText().toString();
                close = t2.getText().toString();

                try {
                    new HttpPostAsyncTask().execute();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Toast.makeText(Predict.this, "Loading", Toast.LENGTH_LONG).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(Predict.this, Result.class));
                        finish();
                    }
                }, 5000);


            }
        });
    }

    private void fillVaules() {
        arrayList.add("Company");
        arrayList.add("Mahindra & Mahindra");
        arrayList.add("Cipla");
        arrayList.add("Infosys");
        arrayList.add("Jindal Steels");
        arrayList.add("Maruti Suzuki");
        arrayList.add("Reliance Power");
        arrayList.add("Tata Powers");
        arrayList.add("Tata Steels");
        arrayList.add("Tata Consultancy Services");
        arrayList.add("Tech Mahindra");
    }
}
