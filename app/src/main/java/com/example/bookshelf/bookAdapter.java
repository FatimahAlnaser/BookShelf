package com.example.bookshelf;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class bookAdapter extends ArrayAdapter<BookModel> {
    Context context;
    int resource;
    dataBase db;

    public bookAdapter(@NonNull Context context, int resource, @NonNull List<BookModel> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource, parent, false);
        TextView bName = (TextView) convertView.findViewById(R.id.nameTextView);
        TextView bPrice = (TextView) convertView.findViewById(R.id.PriceTextView);
        ImageView imgUser = (ImageView) convertView.findViewById(R.id.ImageIcon);
        Button btndelete= (Button) convertView.findViewById(R.id.btndelete1);
        Button btnview= (Button) convertView.findViewById(R.id.btnview1);
        db=new dataBase(context);

        BookModel currentBook = getItem(position);
        bName.setText(currentBook.getName());
         btndelete.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 AlertDialog.Builder builder= new AlertDialog.Builder(context);
                 builder.setTitle("DELETE BOOK");
                 builder.setMessage("Are you sure you want to delete this book?")
                         .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                             @Override
                             public void onClick(DialogInterface dialog, int which) {
                                 Boolean delete = db.DeleteOne(currentBook);
                                 Toast.makeText(context, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                                 Intent intent = new Intent(context.getApplicationContext(), viewBooks.class);
                                 context.startActivity(intent);   }
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

        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //BookModel currentBook = (BookModel)getItem(position);
                if (currentBook != null) {
                    Intent intent = new Intent(context, BookDetails.class);
                    intent.putExtra("bookId", currentBook.getId());
                    context.startActivity(intent);

                }
            }
        });



            bPrice.setText(String.valueOf(currentBook.getPrice()+" SR"));



        Bitmap bitmap = BitmapFactory.decodeByteArray(currentBook.getImage(), 0, currentBook.getImage().length);
        imgUser.setImageBitmap(bitmap);


        return convertView;
    }



}
