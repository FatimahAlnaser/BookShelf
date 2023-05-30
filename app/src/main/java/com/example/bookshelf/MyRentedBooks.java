package com.example.bookshelf;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class MyRentedBooks extends AppCompatActivity {
    ListView RentedList;
    dataBase db;
ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_my_rented_books);
        back=findViewById(R.id.back);
        db=new dataBase(this);
        RentedList=findViewById(R.id.Rentedlist);
        ArrayList<RentedBookModel> MyRented=db.MyRentals(UserInfo.username);
        RentedBooksAdap rentalAdapter = new RentedBooksAdap(this, R.layout.activity_custom_list_view1, MyRented);

        RentedList.setAdapter(rentalAdapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
            }
        });


    }
}