package com.example.bookshelf;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class viewBooks extends AppCompatActivity {
    android.widget.ListView ListViewJava;

    ArrayAdapter ItemArrayAdapter;
    dataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_books);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        db = new dataBase(this);
        ListViewJava = (android.widget.ListView) findViewById(R.id.listView);

        ArrayList<BookModel> items = (ArrayList<BookModel>) db.getBook();

        bookAdapter bookAdapter = new bookAdapter(this, R.layout.activity_custom_list_view, items);
        ListViewJava.setAdapter(bookAdapter);

    }


}
