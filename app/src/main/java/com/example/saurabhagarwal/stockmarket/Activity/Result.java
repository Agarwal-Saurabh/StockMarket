package com.example.saurabhagarwal.stockmarket.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.saurabhagarwal.stockmarket.R;
import com.example.saurabhagarwal.stockmarket.helper.HttpPostAsyncTask;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.example.saurabhagarwal.stockmarket.helper.HttpPostAsyncTask.ans;

public class Result extends AppCompatActivity {

    TextView t1;
    Button b1, b2;
    public static String res[] = new String[7];

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        init();
        method();


    }


    private void init() {

        t1 = (TextView) findViewById(R.id.result);
        b1 = (Button) findViewById(R.id.next);
        b2 = (Button) findViewById(R.id.graph);
        t1.setText(ans);

    }

    private void method() {
        b1.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View view) {
                                      Predict.close = ans;

                                      if (Predict.x < 7) {
                                          res[Predict.x] = ans;
                                          Predict.x++;
                                      }
                                      String dt = Predict.date;  // Start date
                                      SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                                      Calendar c = Calendar.getInstance();
                                      try {
                                          c.setTime(sdf.parse(dt));
                                      } catch (ParseException e) {
                                          e.printStackTrace();
                                      }
                                      c.add(Calendar.DATE, 1);  // number of days to add
                                      Predict.date = sdf.format(c.getTime());
                                      try {
                                          new HttpPostAsyncTask().execute();
                                      } catch (Exception e) {
                                          e.printStackTrace();
                                      }
                                      Toast.makeText(Result.this, Predict.date, Toast.LENGTH_SHORT).show();


                                      new Handler().postDelayed(new Runnable() {
                                          @Override
                                          public void run() {
                                              finish();
                                              startActivity(getIntent());

                                          }
                                      }, 1000);

                                  }
                              }

        );

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Result.this, Graph.class));
            }
        });
    }

}
