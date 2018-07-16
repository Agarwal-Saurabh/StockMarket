package com.example.saurabhagarwal.stockmarket.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.saurabhagarwal.stockmarket.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Dashboard extends AppCompatActivity {

    Button btn1, btn2,btn3;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        init();
        methodListener();
        emailVerification();
    }



    private void init() {

        btn1 = (Button) findViewById(R.id.info);
        btn2 = (Button) findViewById(R.id.predict);
        btn3 = (Button) findViewById(R.id.inv);

    }


    private void methodListener() {

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Dashboard.this, Info.class);
                startActivity(i);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Dashboard.this, Predict.class);
                startActivity(i);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard.this,Inveestment.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemid = item.getItemId();

        switch (itemid)
        {
            case R.id.nav_contact:
                startActivity(new Intent(Dashboard.this,Contact.class));
                break;
            case R.id.nav_about:
                startActivity(new Intent(Dashboard.this,About.class));
                break;

            case R.id.nav_logout:
                Toast.makeText(this, "logout", Toast.LENGTH_SHORT).show();
                SharedPreferences preferences =
                        getSharedPreferences("MyFile", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.commit();
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Dashboard.this, Login.class));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void emailVerification() {

        FirebaseUser user = mAuth.getInstance().getCurrentUser();
        if(  user.isEmailVerified())
        {

        }
        else{
            SharedPreferences preferences =
                    getSharedPreferences("MyFile", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.commit();
            startActivity(new Intent(Dashboard.this,Login.class));
            Toast.makeText(this, "verify your email", Toast.LENGTH_SHORT).show();
        }
    }
}
