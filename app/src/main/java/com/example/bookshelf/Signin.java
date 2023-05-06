package com.example.bookshelf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Signin extends AppCompatActivity {
    EditText username, password;
    Button Login;
    DBhelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        Login= findViewById(R.id.logIn);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        DB = new DBhelper(this);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(user.equals("")||pass.equals(""))
                    Toast.makeText(Signin.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkUser = DB.checkUsername(user);
                    Boolean checkPass = DB.checkUsernamePassword(user,pass);
                    if(!checkPass || !checkUser){
                        Toast.makeText(Signin.this, "Username or password is wrong ", Toast.LENGTH_SHORT).show();
                        }else{
                        ///add intent to home page
                        }
                    }

                }
        });
    }
}