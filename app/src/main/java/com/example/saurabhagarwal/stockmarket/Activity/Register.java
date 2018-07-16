package com.example.saurabhagarwal.stockmarket.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
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
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class Register extends AppCompatActivity {

    EditText editTextName;
    EditText editTextEmail;
    EditText editTextPass;
    EditText editTextCPass;
    EditText editTextPhone;
    private FirebaseAuth mAuth;

    private static final String TAG = "EmailPassword";

   // ArrayList<TouristPojo> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        init();
    }


    private void createAccount(final String email, final String password, final String phone, final String name) {
        final ProgressDialog progress = new ProgressDialog(this);
        progress.setMessage("loading..");
        progress.show();
        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            Toast.makeText(Register.this, "Registration Succesfull", Toast.LENGTH_SHORT).show();

                            //updateDetails(name, email, password, phone);
                            final FirebaseUser user = mAuth.getCurrentUser();
                            user.sendEmailVerification()
                                    .addOnCompleteListener(Register.this, new OnCompleteListener() {
                                        @Override
                                        public void onComplete(@NonNull Task task) {
                                            // Re-enable button

                                            if (task.isSuccessful()) {
                                                Toast.makeText(Register.this, "Verification email sent to " + user.getEmail(),
                                                        Toast.LENGTH_SHORT).show();
                                            } else {
                                                Log.e(TAG, "sendEmailVerification", task.getException());
                                                Toast.makeText(Register.this, "Failed to send verification email.",
                                                        Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                            Intent intent = new Intent(Register.this, Dashboard.class);
                            startActivity(intent);
                            finish();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Register.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // [START_EXCLUDE]
                        progress.hide();
                        // [END_EXCLUDE]
                    }
                });


        // [END create_user_with_email]
    }

    private void init() {


        editTextName = (EditText) findViewById(R.id.name);
        editTextEmail = (EditText) findViewById(R.id.email);
        editTextPass = (EditText) findViewById(R.id.password);
        editTextCPass = (EditText) findViewById(R.id.Cpassword);
        editTextPhone = (EditText) findViewById(R.id.phone);
    }

    public void remgister(View v) {
        String name = editTextName.getText().toString();
        String email = editTextEmail.getText().toString();
        String pass = editTextPass.getText().toString();
        String Cpass = editTextCPass.getText().toString();
        String phone = editTextPhone.getText().toString();

        boolean flag = false;
        if (!ValidationHelper.validateName(name)) {
            editTextName.requestFocus();
            editTextName.setError("Enter Valid Name");
            flag = true;
        } else {
            editTextName.setError(null);
        }
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
        if (!ValidationHelper.validatePassword(Cpass)) {
            editTextCPass.requestFocus();
            editTextCPass.setError("Min Length 6");
            flag = true;
        } else {
            editTextCPass.setError(null);
        }
        if (!pass.equals(Cpass)) {
            editTextCPass.requestFocus();
            editTextCPass.setError("password must match");
            flag = true;
        } else {
            editTextCPass.setError(null);
        }
        if (!ValidationHelper.validateTelephone(phone)) {
            editTextPhone.requestFocus();
            editTextPhone.setError("invalid");
            flag = true;
        } else {
            editTextPhone.setError(null);
        }

        if (!flag) {

            createAccount(email, pass, phone, name);

        }
    }

   /* private void updateDetails(String Uname, String Uemail, String Upass, String Uphone) {

        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String Uid = currentFirebaseUser.getUid();
        UserPojo userPojo = new UserPojo();

        userPojo.setEmailId(Uemail);
        userPojo.setPassword(Upass);
        userPojo.setPhone(Uphone);
        userPojo.setUsername(Uname);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref1, ref2, ref3;
        ref1 = database.getReference();
        ref2 = ref1.child("User");
        ref3 = ref2.child(Uid);
        ref3.setValue(userPojo);
    }
*/
    public void login(View view) {
        Intent intent = new Intent(Register.this, Login.class);
        startActivity(intent);
        finish();
    }


}
