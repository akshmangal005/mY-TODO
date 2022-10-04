package com.example.mytodo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signup extends AppCompatActivity {
    Button loginbtn, registerbtn;
    TextInputLayout fullname_var, username_var, email_var, phone_var, password_var;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        loginbtn = findViewById(R.id.loginbtn);
        registerbtn = findViewById(R.id.register);
        fullname_var = findViewById(R.id.username_fullname);
        username_var = findViewById(R.id.username_field);
        phone_var = findViewById(R.id.phone_number);
        email_var = findViewById(R.id.email);
        password_var = findViewById(R.id.username_password);


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), login.class);
                startActivity(intent);
                finish();
            }
        });

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = username_var.getEditText().getText().toString();
                String fullname = fullname_var.getEditText().getText().toString();
                String password = password_var.getEditText().getText().toString();
                String phone = phone_var.getEditText().getText().toString();
                String email = email_var.getEditText().getText().toString();

                if (!fullname.isEmpty()) {
                    fullname_var.setError(null);
                    fullname_var.setErrorEnabled(false);
                    if (!username.isEmpty()) {
                        username_var.setError(null);
                        username_var.setErrorEnabled(false);
                        if (!email.isEmpty()) {
                            email_var.setError(null);
                            email_var.setErrorEnabled(false);
                            if (!phone.isEmpty()) {
                                phone_var.setError(null);
                                phone_var.setErrorEnabled(false);
                                if (!password.isEmpty()) {
                                    password_var.setError(null);
                                    password_var.setErrorEnabled(false);
                                    if(email.matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$"))
                                    {

                                        firebaseDatabase = FirebaseDatabase.getInstance();
                                        reference = FirebaseDatabase.getInstance().getReference("userdata");


                                        String username_d = username_var.getEditText().getText().toString();
                                        String fullname_d = fullname_var.getEditText().getText().toString();
                                        String password_d = password_var.getEditText().getText().toString();
                                        String phone_d = phone_var.getEditText().getText().toString();
                                        String email_d = email_var.getEditText().getText().toString();
                                        storingdata storingdata_ = new storingdata(fullname_d,username_d,email_d,phone_d,password_d);
                                        reference.child(username_d).setValue(storingdata_);

                                        Toast.makeText(getApplicationContext(),"Register Successful",Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(),Dashboard.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else
                                        email_var.setError("Enter Valid Email");
                                } else
                                    password_var.setError("Please Password");
                            } else
                                phone_var.setError("Enter Phone Number");
                        } else
                            email_var.setError("Enter email");
                    } else
                        username_var.setError("Enter User Name");

                } else
                    fullname_var.setError("Enter Full Name");

            }
        });


    }
}