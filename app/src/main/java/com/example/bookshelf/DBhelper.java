package com.example.bookshelf;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBhelper extends SQLiteOpenHelper {

    public static final String DBNAME = "BOOKSHELF.db";
    public static final String USERSTABLE = "users";
    public static final String USERNAMECOL = "username";
    public static final String PASSWORDCOL = "password";

    public DBhelper(@Nullable Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create Table " + USERSTABLE + "(" + USERNAMECOL + " TEXT primary key, " + PASSWORDCOL + " TEXT)");
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
        Cursor cursor = MyDB.rawQuery("Select * from " + USERSTABLE + " where " + USERNAMECOL + " = ? and " + PASSWORDCOL + " = ?", new String[] {username,password});
        if(cursor.getCount()>0) return true;
        return false;
    }
}

