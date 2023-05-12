package com.example.bookshelf;

import static com.example.bookshelf.R.id.ImageView1;
import static com.example.bookshelf.R.id.imageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddBook extends AppCompatActivity {

    Button btn_add;
    EditText book_name, book_price,book_stat;
    DBhelper dataBaseHelper;

    ListView lv_bookList;

    ArrayAdapter booksArrayAdapter;

    ImageView photo ;

    FloatingActionButton button;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        photo = findViewById( ImageView1);
        button = findViewById(R.id.uplode);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(AddBook.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();

            }
        });






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


                dataBase dataBaseHelper = new dataBase(AddBook.this);

                boolean success = dataBaseHelper.addOne(newBook);

                Toast.makeText(AddBook.this,"Success= "+ success ,Toast.LENGTH_SHORT).show();
                //ShowCustomerListView(dataBaseHelper);


            }
        });


    }




    protected void onActivityResult(int requestCode, int resultCode,@Nullable Intent data){
super.onActivityResult(requestCode,resultCode,data);
Uri uri=data.getData();
photo.setImageURI(uri);
    }
}