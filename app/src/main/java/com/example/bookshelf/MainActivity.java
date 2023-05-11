package com.example.bookshelf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

     Button register, signin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register = findViewById(R.id.btnregister);
        signin = findViewById(R.id.btnsignin);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), Signin.class);
                startActivity(intent);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), register.class);
                startActivity(intent);
            }
        });

    }


}