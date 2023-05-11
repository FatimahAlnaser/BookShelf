package com.example.bookshelf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class DeleteBook extends AppCompatActivity {
    ListView lv_BookList;
    ArrayAdapter bookArrayAdapter;
    DBhelper dataBaseHelper;
Button btnview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_book);
        lv_BookList = findViewById(R.id.lv_BooksList);
         btnview=findViewById(R.id.btnviews);

      dataBaseHelper = new DBhelper(DeleteBook.this);
        ShowBookOnListView(dataBaseHelper);

    }
btnview.OnClickListener(new View.OnClickListener()){



    });
        btnview.setOnClickListener( new View.OnClickListener() {
        public void onClick(View v){
            dataBaseHelper = new DBhelper(DeleteBook.this);
            ShowBookOnListView(dataBaseHelper);

            //Toast.makeText(MainActivity.this, everyone.toString(), Toast.LENGTH_SHORT).show();
        }
    });

  lv_BookList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            BookModel ClickedBook = (BookModel) adapterView.getItemAtPosition(i);
            dataBaseHelper.DeleteOne(ClickedBook);
            ShowBookOnListView(dataBaseHelper);
            Toast.makeText(DeleteBook.this, "Deleted" + ClickedBook.toString(), Toast.LENGTH_SHORT).show();
        }
    });
    private void ShowBookOnListView(DBhelper dataBaseHelper) {
        bookArrayAdapter = new ArrayAdapter<BookModel>(DeleteBook.this, android.R.layout.simple_list_item_1, dataBaseHelper.getBook());
        lv_BookList.setAdapter(bookArrayAdapter);
    }
}