package com.example.bookshelf;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AlertDialog.Builder;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class Home extends AppCompatActivity {
private Button LogOutButton;
private Button add;

private Button view,Rented;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_home);
        LogOutButton=findViewById(R.id.LogOutButton);
        add=findViewById(R.id.btnadd);
        view=findViewById(R.id.btnview);
        Rented=findViewById(R.id.btnRented);
        LogOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               final Intent LogOutButton=new Intent(Home.this,MainActivity.class);
                AlertDialog.Builder builder= new Builder(Home.this);
                builder.setTitle("Sign Out");
                builder.setMessage("Are you sure to Sign out?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(LogOutButton);
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

               AlertDialog alert = builder.create();
                alert.show();
                Button nbutton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
                nbutton.setTextColor(Color.BLACK);
                Button pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
                pbutton.setTextColor(Color.BLACK);

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddBook.class);
                startActivity(intent);
            }
        });


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), viewBooks.class);
                startActivity(intent);
            }
        });

        Rented.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyRentedBooks.class);
                startActivity(intent);
            }
        });
    }
}


