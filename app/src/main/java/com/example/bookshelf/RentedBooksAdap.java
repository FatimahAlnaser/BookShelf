package com.example.bookshelf;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class RentedBooksAdap extends ArrayAdapter<RentedBookModel> {
    Context context;
    int resource;
    dataBase db;

    public RentedBooksAdap(@NonNull Context context, int resource, @NonNull List<RentedBookModel> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;


    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource, parent, false);
        TextView bName = (TextView) convertView.findViewById(R.id.nameTextView);
        TextView bPrice = (TextView) convertView.findViewById(R.id.PriceTextView);
        ImageView imgUser = (ImageView) convertView.findViewById(R.id.ImageIcon);
        Button btnreturn = (Button) convertView.findViewById(R.id.returnbtn);
        db = new dataBase(context);

        RentedBookModel currentBook = getItem(position);
        bName.setText(currentBook.getBname());
        bPrice.setText(String.valueOf(currentBook.getPrice() + " SR"));


        Bitmap bitmap = BitmapFactory.decodeByteArray(currentBook.getImage(), 0, currentBook.getImage().length);
        imgUser.setImageBitmap(bitmap);

       btnreturn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Boolean Return = db.Return(currentBook);
               Intent intent = new Intent(context.getApplicationContext(), MyRentedBooks.class);
               context.startActivity(intent);
           }
       });
        return convertView;
    }

}
