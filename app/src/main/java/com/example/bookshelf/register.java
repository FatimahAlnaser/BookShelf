package com.example.bookshelf;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class register extends AppCompatActivity {
    EditText username, password,Email,phonenum;
    Button signup;
    DBhelper DB;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_register);
        username = findViewById(R.id.username3);
        password = findViewById(R.id.password3);
        Email = findViewById(R.id.email);
        phonenum = findViewById(R.id.phonenumber);

        signup = findViewById(R.id.signup);
        DB = new DBhelper(this);


        signup.setOnClickListener(new View.OnClickListener() {


            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String email = Email.getText().toString();
                String phonen = phonenum.getText().toString();


                if (user.equals("") || pass.equals("") || email.equals("") || phonen.equals(""))
                    Toast.makeText(register.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkUser = DB.checkUsername(user);
                    Boolean checkemail = DB.checkEmail(email);
                    Boolean checkphone = DB.checkPhone(phonen);
                    if (!checkUser&& !checkemail && !checkphone) {
                        Boolean insert = DB.insertData(user, pass,email,phonen);
                        if (insert) {
                            Toast.makeText(register.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), Home.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(register.this, "Registration failed", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(register.this, "Already exists! please sign in", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



    }
}