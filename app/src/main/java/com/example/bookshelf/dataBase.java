package com.example.bookshelf;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class dataBase extends SQLiteOpenHelper {

    public static final String BOOK_TABLE = "BOOK_TABLE";
    public static final String COLUMN_BOOK_NAME = "BOOK_NAME";
    public static final String COLUMN_BOOK_PRICE = "CUSTOMER_PRICE";
    public static final String COLUMN_BOOK_STATE = "BOOK_STATE";
    public static final String BOOK_ID = "ID";


    public dataBase(@Nullable Context context) {
        super(context, "Book.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createDataBase ="CREATE TABLE "+ BOOK_TABLE +" ( "+ BOOK_ID +  " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_BOOK_NAME+ " TEXT , "+ COLUMN_BOOK_PRICE +" INT , " +COLUMN_BOOK_STATE +" TEXT)";

        db.execSQL(createDataBase);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(BookModel book){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_BOOK_NAME,book.getName());
        cv.put(COLUMN_BOOK_PRICE,book.getPrice());
        cv.put(COLUMN_BOOK_STATE,book.getState());


        long insert = db.insert(BOOK_TABLE, null, cv);
        if (insert== -1){return false;}
        else {return true;}

    }

    /////DELETE
    public List<BookModel> getBook(){
        List<BookModel> returnList = new ArrayList<>();
// check the where condition *****************************************************************************************
        String queryString ="SELECT * FROM "+ BOOK_TABLE ;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString,null);

        if (cursor.moveToFirst()){
            do{
                int bookID = cursor.getInt(0);
                String BookName = cursor.getString(1);
                int BookPrice = cursor.getInt(2);
                String BookState = cursor.getString(3);

                BookModel newCustomer = new BookModel(bookID, BookName, BookPrice, BookState);
                returnList.add(newCustomer);

            }while (cursor.moveToNext());

        }else{

        }

        cursor.close();
        db.close();


        return returnList;

    }
    public boolean DeleteOne(BookModel BookMod){
        SQLiteDatabase db = this.getWritableDatabase();
        //////check the where condition **********************************************************************************************
        String queryString= "Delete From " + BOOK_TABLE ;
        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()){
            return true;
        } else{

            return false;
        }

    }
}

