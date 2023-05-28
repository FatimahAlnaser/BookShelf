package com.example.bookshelf;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class bookAdapter extends ArrayAdapter<BookModel> {
    Context context;
    int resource;
    public bookAdapter(@NonNull Context context, int resource, @NonNull List<BookModel> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource, parent, false  );
        TextView bName = (TextView)convertView.findViewById(R.id.nameTextView);
        TextView bPrice = (TextView)convertView.findViewById(R.id.PriceTextView);
        ImageView imgUser = (ImageView) convertView.findViewById(R.id.ImageIcon);
        BookModel currentItem = getItem(position);
        bName.setText(currentItem.getName());


        if ( currentItem.getPrice() ==0){
            bPrice.setText("");
        }else {
            bPrice.setText(String.valueOf(currentItem.getPrice()));
        }


        Bitmap bitmap = BitmapFactory.decodeByteArray(currentItem.getImage(), 0, currentItem.getImage().length);
        imgUser.setImageBitmap(bitmap);


        return convertView;
    }
}
