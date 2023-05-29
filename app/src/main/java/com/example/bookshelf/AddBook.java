package com.example.bookshelf;

import static com.example.bookshelf.R.id.imageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;


import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class AddBook extends AppCompatActivity {

    Button btn_add,btnupload;
    EditText book_name, book_price, book_stat,book_author;
    dataBase dataBaseHelper;


    ImageView back,imageView;
    byte[] image = null;




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_add_book);

        btnupload=findViewById(R.id.upload);
        back = findViewById(R.id.imageButton);


        btn_add = findViewById(R.id.btn_add);

        book_name = findViewById(R.id.book_name);
        book_price = findViewById(R.id.book_price);
        book_stat = findViewById(R.id.book_stat);
        book_author=findViewById(R.id.book_author);
        imageView=(ImageView)findViewById(R.id.imageView);

        dataBaseHelper = new dataBase(this);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookModel newBook;
                String book_name1 = book_name.getText().toString();
                String book_Price = book_price.getText().toString();
                String book_State = book_stat.getText().toString();
                String book_author1=book_author.getText().toString();
                BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();



                if (book_Price.equals("") || book_name1.equals("") || book_State.equals("") || drawable ==null|| book_author1.equals(""))
                    Toast.makeText(AddBook.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else {

                    try {
                        Bitmap bitmap = drawable.getBitmap();
                        image = getBytes(bitmap);
                        newBook = new BookModel(-1, book_name.getText().toString(), Integer.parseInt(book_price.getText().toString()), book_stat.getText().toString(), image,book_author.getText().toString(),UserInfo.username);
                        Toast.makeText(AddBook.this, newBook.toString(), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(AddBook.this, "Error creating customer", Toast.LENGTH_SHORT).show();
                        newBook = new BookModel(-1, "Error", 0, "Error", null,"","");
                    }


                    //dataBase dataBaseHelper = new dataBase(AddBook.this);

                    boolean success = dataBaseHelper.addOne(newBook);

                    Toast.makeText(AddBook.this, "Added successfully", Toast.LENGTH_SHORT).show();


                    Intent intent = new Intent(getApplicationContext(), Home.class);
                    startActivity(intent);
                    //ShowCustomerListView(dataBaseHelper);

                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
            }
        });



        btnupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 3);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();


            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap decodeStream = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(decodeStream);
            } catch (Exception e) {
                Log.e("there is an Exception", e.getMessage());
            }


        }
    }

    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

}
