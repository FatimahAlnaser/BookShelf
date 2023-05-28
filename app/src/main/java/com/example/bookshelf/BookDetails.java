package com.example.bookshelf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class BookDetails extends AppCompatActivity {

    Button Rent,Return;
    Context context;
    TextView name,price,author,state;
    ImageView image,back;
    dataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
         getSupportActionBar().hide();
        setContentView(R.layout.activity_book_details);

        Intent intent = getIntent();
        int bookId = intent.getIntExtra("bookId", -1);
        db = new dataBase(this);
        BookModel book=db.getDetails(bookId);
        if(book!=null) {

            name = (TextView) findViewById(R.id.Name);
            price = (TextView) findViewById(R.id.Price);
            state = (TextView) findViewById(R.id.State);
            author = (TextView) findViewById(R.id.Author);
            image = (ImageView) findViewById(R.id.imageBook);


            name.setText("Book name: "+book.getName());
            price.setText(String.valueOf("Book price: "+book.getPrice()+" SR"));
            state.setText("Book status:"+book.getState());
            Bitmap bitmap = BitmapFactory.decodeByteArray(book.getImage(), 0, book.getImage().length);
            image.setImageBitmap(bitmap);
        }
       else {
            Toast.makeText(BookDetails.this, " Error", Toast.LENGTH_SHORT).show();
        }
         back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), viewBooks.class);
                startActivity(intent);
            }
        });

    }

}