package com.example.saurabhagarwal.stockmarket.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.example.saurabhagarwal.stockmarket.R;
import com.example.saurabhagarwal.stockmarket.helper.ValidationHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText editTextEmail;
    EditText editTextPass;
    private FirebaseAuth mAuth;
    String email, pass;
    private static final String TAG = "EmailPassword";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences preferences =
                getSharedPreferences("MyFile", MODE_PRIVATE);
        if (preferences.getBoolean("isLogin", false)) {
            startActivity(new Intent(this, Dashboard.class));
            finish();
        }

        mAuth = FirebaseAuth.getInstance();

        init();
    }

    private void signIn(String email, String password) {

        Log.d(TAG, "signIn:" + email);


        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("loading..");
        dialog.show();


        // [START sign_in_with_email]

        mAuth.signInWithEmailAndPassword(email, password)

                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override

                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            // Sign in success, update UI with the signed-in user's information

                            Log.d(TAG, "signInWithEmail:success");
                            SharedPreferences preferences = getSharedPreferences("MyFile", MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putBoolean("isLogin", true);
                            editor.commit();
                            Intent i = new Intent(Login.this, Dashboard.class);
                            startActivity(i);
                            finish();

                        } else {

                            // If sign in fails, display a message to the user.

                            Log.w(TAG, "signInWithEmail:failure", task.getException());

                            Toast.makeText(Login.this, "Authentication failed.",

                                    Toast.LENGTH_SHORT).show();
                        }


                        // [START_EXCLUDE]


                        dialog.hide();


                        // [END_EXCLUDE]

                    }

                });

        // [END sign_in_with_email]

    }

    private void init() {
        editTextEmail = (EditText) findViewById(R.id.email);
        editTextPass = (EditText) findViewById(R.id.password);
    }

    public void login(View v) {
        email = editTextEmail.getText().toString();
        pass = editTextPass.getText().toString();
        boolean flag = false;
        if (!ValidationHelper.validateEmail(email)) {
            editTextEmail.requestFocus();
            editTextEmail.setError("Enter Valid Email");
            flag = true;
        } else {
            editTextEmail.setError(null);
        }
        if (!ValidationHelper.validatePassword(pass)) {
            editTextPass.requestFocus();
            editTextPass.setError("Min Length 6");
            flag = true;
        } else {
            editTextPass.setError(null);
        }
        if (!flag) {
            signIn(email, pass);
        }
    }


    public void register(View v) {
        Intent i = new Intent(this, Register.class);
        startActivity(i);
    }

    public void forgot(View v) {

        email = editTextEmail.getText().toString();
        ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Loading..");
        pd.show();
        if (email != null && email != "") {
            mAuth.sendPasswordResetEmail(email)

                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Login.this, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Login.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

        } else {
            Toast.makeText(this, "Enter email", Toast.LENGTH_SHORT).show();
        }
        pd.hide();
    }
}
