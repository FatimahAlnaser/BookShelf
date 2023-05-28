package com.example.bookshelf;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.concurrent.atomic.AtomicReference;

public class DeleteBook extends AppCompatActivity {
    ListView lv_BookList;
    ArrayAdapter bookArrayAdapter;
    dataBase dataBaseHelper;

    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_delete_book);

        back=findViewById(R.id.imageButton1);

        dataBaseHelper = new dataBase(DeleteBook.this);







///////////////////////////////////////////////////////////////////////////////////////
        lv_BookList.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder builder= new AlertDialog.Builder(DeleteBook.this);
                builder.setTitle("DELETE BOOK");
                builder.setMessage("Are you sure you want to delete this Book?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                BookModel ClickedBook = (BookModel) adapterView.getItemAtPosition(i);
                                dataBaseHelper.DeleteOne(ClickedBook);

                                Toast.makeText(DeleteBook.this, "Book has been Deleted successfully", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
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



    }
}