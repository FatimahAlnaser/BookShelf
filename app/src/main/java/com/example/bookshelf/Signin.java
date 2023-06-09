package com.example.bookshelf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Signin extends AppCompatActivity {
    EditText username, password;
    Button Login;
    dataBase DB;

    ImageView back;

    String user,pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_signin);
        Login= findViewById(R.id.logIn);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        back=findViewById(R.id.imageButton);
        DB = new dataBase(this);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 user = username.getText().toString();
                pass = password.getText().toString();

                if(user.equals("")||pass.equals(""))
                    Toast.makeText(Signin.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                 else{
                    Boolean checkUser = DB.checkUsername(user);
                    Boolean checkPass = DB.checkUsernamePassword(user,pass);
                    if(!checkPass || !checkUser){
                        Toast.makeText(Signin.this, "Username or password is wrong ", Toast.LENGTH_SHORT).show();
                        }else{
                        Toast.makeText(Signin.this, "Signed in Successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), Home.class);
                                    UserInfo.username=user;
                                    startActivity(intent);


                        }
                    }

                }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}