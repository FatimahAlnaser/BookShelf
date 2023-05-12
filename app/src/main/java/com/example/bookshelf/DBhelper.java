package com.example.bookshelf;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBhelper extends SQLiteOpenHelper {

    public static final String DBNAME = "BOOKSHELFF.db";
    public static final String USERSTABLE = "users";
    public static final String USERNAMECOL = "username";
    public static final String PASSWORDCOL = "password";

    public static final String EMAILCOL="email";

    public static final String PhoneNumCOL="phonenum";


    public static final String BOOK_TABLE = "BOOK_TABLE";
    public static final String COLUMN_BOOK_NAME = "BOOK_NAME";
    public static final String COLUMN_BOOK_PRICE = "CUSTOMER_PRICE";
    public static final String COLUMN_BOOK_STATE = "BOOK_STATE";
    public static final String BOOK_ID = "ID";

    public DBhelper(@Nullable Context context) {
        super(context, DBNAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create Table " + USERSTABLE + "(" + USERNAMECOL + " TEXT primary key, " + PASSWORDCOL + " TEXT, " + EMAILCOL +" TEXT, "+ PhoneNumCOL +" TEXT)");
        sqLiteDatabase.execSQL("create Table " + BOOK_TABLE + "(" + BOOK_ID + " INTEGER primary key, " + COLUMN_BOOK_NAME + " TEXT, " + COLUMN_BOOK_PRICE +" INTEGER, "+ COLUMN_BOOK_STATE + " TEXT," +COLUMN_BOOK_STATE+ "TEXT,"+ "USERNAME TEXT, FOREIGN KEY('USERNAME') "+ "REFERENCES "+ USERSTABLE + "("+USERNAMECOL+"))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public boolean addOne(BookModel book){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(BOOK_ID,book.getId());
        cv.put(COLUMN_BOOK_NAME,book.getName());
        cv.put(COLUMN_BOOK_PRICE,book.getPrice());
        cv.put(COLUMN_BOOK_STATE,book.getState());


        long insert = db.insert(BOOK_TABLE, null, cv);
        if (insert== -1){return false;}
        else {return true;}

    }


    public List<BookModel> getEveryone(){
        List<BookModel> returnList = new ArrayList<>();

        String queryString ="SELECT * FROM "+ BOOK_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString,null);

        if (cursor.moveToFirst()){
            do{
                int bookID = cursor.getInt(0);
                String BookName = cursor.getString(1);
                int BookPrice = cursor.getInt(2);
                String BookState = cursor.getString(3);
                String user =cursor.getString(4);

                BookModel newCustomer = new BookModel(bookID, BookName, BookPrice, BookState);
                returnList.add(newCustomer);

            }while (cursor.moveToNext());

        }else{

        }

        cursor.close();
        db.close();


        return returnList;

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



    public Boolean insertData(String username, String password, String email, String phonenumber){
        SQLiteDatabase MyDB = this.getWritableDatabase();

        ContentValues contentValues= new ContentValues();
        contentValues.put(USERNAMECOL, username);
        contentValues.put(PASSWORDCOL, password);
        contentValues.put(EMAILCOL, email);contentValues.put(PhoneNumCOL, phonenumber);

        long result = MyDB.insert(USERSTABLE, null, contentValues);
        if(result==-1) return false;
        return true;
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
        String queryString= "Delete From " + BOOK_TABLE + " WHERE "+ BOOK_ID+" = " + BookMod.getId() ;
        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()){
            return true;
        } else{

            return false;
        }

    }


}