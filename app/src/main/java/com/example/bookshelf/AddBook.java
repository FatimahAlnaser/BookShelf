package com.example.bookshelf;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class AddBook extends AppCompatActivity {

    Button btn_add;
    EditText book_name, book_price,book_stat;
    DBhelper dataBaseHelper;

    ListView lv_bookList;

    ArrayAdapter booksArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        btn_add=findViewById(R.id.btn_add);

        book_name=findViewById(R.id.book_name);
        book_price=findViewById(R.id.book_price);
        book_stat=findViewById(R.id.book_stat);

        dataBaseHelper = new DBhelper(AddBook.this);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookModel newBook;

                try {
                    newBook= new BookModel(-1, book_name.getText().toString(), Integer.parseInt(book_price.getText().toString()),  book_stat.getText().toString());
                    Toast.makeText(AddBook.this, newBook.toString(),Toast.LENGTH_SHORT).show();

                }catch (Exception e){
                    Toast.makeText(AddBook.this, "Error creating customer",Toast.LENGTH_SHORT).show();
                    newBook = new BookModel(-1 , "Error",0,"Error");
                }


                DBhelper dataBaseHelper = new DBhelper(AddBook.this);

                boolean success = dataBaseHelper.addOne(newBook);

                Toast.makeText(AddBook.this,"Success= "+ success ,Toast.LENGTH_SHORT).show();
                ShowCustomerListView(dataBaseHelper);


            }
        });


    }

    private void ShowCustomerListView(DBhelper dataBaseHelper2) {
        booksArrayAdapter = new ArrayAdapter<BookModel>(AddBook.this , android.R.layout.simple_list_item_1 , dataBaseHelper2.getEveryone());
        lv_bookList.setAdapter( booksArrayAdapter);
    }
}