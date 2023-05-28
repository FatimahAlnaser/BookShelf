package com.example.bookshelf;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.Nullable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;


public class dataBase extends SQLiteOpenHelper {

    public static final String BOOK_TABLE = "BOOK_TABLE";
    public static final String COLUMN_BOOK_NAME = "BOOK_NAME";
    public static final String COLUMN_BOOK_PRICE = "CUSTOMER_PRICE";
    public static final String COLUMN_BOOK_STATE = "BOOK_STATE";
    public static final String BOOK_ID = "ID";

    public static final String USERSTABLE = "users";
    public static final String USERNAMECOL = "username";
    public static final String PASSWORDCOL = "password";

    public static final String EMAILCOL="email";

    public static final String PhoneNumCOL="phonenum";


    public dataBase(@Nullable Context context) {
        super(context, "Book_shelf.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createDataBase ="CREATE TABLE "+ BOOK_TABLE +" ( "+ BOOK_ID +  " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_BOOK_NAME+ " TEXT , "+ COLUMN_BOOK_PRICE +" INT , " +COLUMN_BOOK_STATE +" TEXT ,image BLOB)";

        db.execSQL(createDataBase);
        db.execSQL("create Table " + USERSTABLE + "(" + USERNAMECOL + " TEXT primary key, " + PASSWORDCOL + " TEXT, " + EMAILCOL +" TEXT, "+ PhoneNumCOL +" TEXT)");
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
        cv.put("image",book.getImage());


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
                @SuppressLint("Range") byte[] image = cursor.getBlob(cursor.getColumnIndex("image"));


                BookModel newCustomer = new BookModel(bookID, BookName, BookPrice, BookState,image);
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
        String queryString= "Delete From " + BOOK_TABLE + " Where "+BOOK_ID+" ="+BookMod.getId();
        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()){
            return true;
        } else{

            return false;
        }

    }

    public Boolean checkUsername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from " + USERSTABLE + " where " + USERNAMECOL + " = ?", new String[]{username});
        if (cursor.getCount() > 0) return true;
        return false;
    }

    public Boolean checkUsernamePassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from " + USERSTABLE + " where " + USERNAMECOL + " = ? and " + PASSWORDCOL + " = ? ", new String[] {username,password});
        if(cursor.getCount()>0) return true;
        return false;
    }

    public Boolean checkEmail( String email){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from " + USERSTABLE + " where " + EMAILCOL + " = ?" , new String[] {email});
        if(cursor.getCount()>0) return true;
        return false;
    }

    public Boolean checkPhone( String phone){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from " + USERSTABLE + " where " + PhoneNumCOL + " = ?" , new String[] {phone});
        if(cursor.getCount()>0) return true;
        return false;
    }



    public Boolean insertData(String username, String password, String email, String phonenumber){
        SQLiteDatabase MyDB = this.getWritableDatabase();

        ContentValues contentValues= new ContentValues();
        contentValues.put(USERNAMECOL, username);
        contentValues.put(PASSWORDCOL, password);
        contentValues.put(EMAILCOL, email);
        contentValues.put(PhoneNumCOL, phonenumber);

        long result = MyDB.insert(USERSTABLE, null, contentValues);
        if(result==-1) return false;
        return true;
    }


    public BookModel getDetails(int id){

        String queryString;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + BOOK_TABLE + " where ID=" +id , null);
        BookModel bookModel=null;

        if (cursor.moveToFirst()) {



                @SuppressLint("Range") int bookid = cursor.getInt(cursor.getColumnIndex(BOOK_ID));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(COLUMN_BOOK_NAME));
                @SuppressLint("Range") int price = cursor.getInt(cursor.getColumnIndex(COLUMN_BOOK_PRICE));
                 @SuppressLint("Range") String status =cursor.getString(cursor.getColumnIndex(COLUMN_BOOK_STATE));
                @SuppressLint("Range") byte[] image = cursor.getBlob(cursor.getColumnIndex("image"));



                bookModel = new BookModel(bookid,name,price,status,image);
           }

         return bookModel;
        }

    }




