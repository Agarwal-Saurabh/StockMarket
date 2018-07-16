package com.example.saurabhagarwal.stockmarket.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.saurabhagarwal.stockmarket.R;

import java.util.ArrayList;

public class Inveestment extends AppCompatActivity {

    Button btn;
    Spinner spinner;
    CheckBox flexible, hard;
    EditText t1, t2;
    String text;
    public static int amount, gain, days;
    int flag;
    ArrayList<String> arrayList = new ArrayList<>();
    private ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inveestment);
        init();
        methodListner();
        fillVaules();
        arrayAdapter = new ArrayAdapter(Inveestment.this, R.layout.list_item, arrayList);
        spinner.setAdapter(arrayAdapter);
    }

    private void init() {
        btn = (Button) findViewById(R.id.invest);
        t1 = (EditText) findViewById(R.id.amount);
        t2 = (EditText) findViewById(R.id.gain);
        spinner = (Spinner) findViewById(R.id.days);
        flexible = (CheckBox) findViewById(R.id.flexible);
        hard = (CheckBox) findViewById(R.id.hard);
    }


    private void methodListner() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                text = arrayList.get(position);


                // Toast.makeText(Predict.this, company, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        flexible.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //is chkIos checked?
                if (((CheckBox) v).isChecked()) {
                    hard.setChecked(false);
                    flag = 1;
                }

            }
        });

        hard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //is chkIos checked?
                if (((CheckBox) v).isChecked()) {
                    flexible.setChecked(false);
                    flag = 2;
                }

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                amount = Integer.parseInt(t1.getText().toString());
                gain = Integer.parseInt(t2.getText().toString());
                days = Integer.parseInt(text);
                if (flag == 1)
                    startActivity(new Intent(Inveestment.this, Flexible.class));
                if (flag == 2)
                    startActivity(new Intent(Inveestment.this, Hard.class));
            }
        });

    }


    private void fillVaules() {
        arrayList.add("Days");
        arrayList.add("1");
        arrayList.add("7");
        arrayList.add("14");
        arrayList.add("28");
    }


}
