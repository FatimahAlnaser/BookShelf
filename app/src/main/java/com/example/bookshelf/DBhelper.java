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

    public static final String DBNAME = "BOOKSHELF1.db";
    public static final String USERSTABLE = "users";
    public static final String USERNAMECOL = "username";
    public static final String PASSWORDCOL = "password";

    public static final String EMAILCOL="email";

    public static final String PhoneNumCOL="phonenum";




    public DBhelper(@Nullable Context context) {
        super(context, DBNAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create Table " + USERSTABLE + "(" + USERNAMECOL + " TEXT primary key, " + PASSWORDCOL + " TEXT, " + EMAILCOL +" TEXT, "+ PhoneNumCOL +" TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
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
}

