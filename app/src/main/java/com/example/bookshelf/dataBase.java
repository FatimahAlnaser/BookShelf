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
import java.util.function.DoubleBinaryOperator;


public class dataBase extends SQLiteOpenHelper {
    public static final String DBNAME = "BSProjectApp1.db";
    public static final String BOOK_TABLE = "BOOK";
    public static final String COLUMN_BOOK_NAME = "BookName";
    public static final String COLUMN_BOOK_PRICE = "BookPrice";
    public static final String COLUMN_BOOK_STATE = "BookState";

    public static final  String COLUMN_BOOK_AUTHOR="BookAuthor";
    public static final  String COLUMN_BOOK_USERNAME="username";
    public static final String BOOK_ID = "BookID";

    public static final String USERSTABLE = "User";
    public static final String USERNAMECOL = "username";
    public static final String PASSWORDCOL = "password";

    public static final String EMAILCOL="Email";

    public static final String PhoneNumCOL="phonenum";

    //Rent Book

    public static final String ORDERSTABLE = "Orders";

    public static final String COLUMN_ORDER_ID = "OrderID";

    public static final String COLUMN_ORDER_UESRNAME = "username";

    public static final String COLUMN_ORDER_IDBOOK= "RentedBookID";
    public static final String COLUMN_ORDER_NAME= "RentedBookName";
    public static final String COLUMN_ORDER_PRICE= "RentedBookPrice";
    public static final String COLUMN_ORDER_IMAGE= "RentedBookimage";




    public dataBase(@Nullable Context context) {
        super(context, DBNAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createDataBase ="CREATE TABLE "+ BOOK_TABLE +" ( "+ BOOK_ID +  " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_BOOK_NAME+ " TEXT , "+ COLUMN_BOOK_PRICE +" INT , " +COLUMN_BOOK_STATE +" TEXT ,image BLOB ," +COLUMN_BOOK_AUTHOR+" TEXT,"+ COLUMN_BOOK_USERNAME +" TEXT, "+" FOREIGN KEY ("+COLUMN_BOOK_USERNAME +") REFERENCES "+ USERSTABLE +"("+USERNAMECOL+")"+")";
        db.execSQL(createDataBase);

        db.execSQL("create Table " + USERSTABLE + "(" + USERNAMECOL + " TEXT primary key, " + PASSWORDCOL + " TEXT, " + EMAILCOL +" TEXT, "+ PhoneNumCOL +" TEXT)");
        db.execSQL("create Table " + ORDERSTABLE+ "(" +COLUMN_ORDER_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+COLUMN_ORDER_UESRNAME+" TEXT, "+COLUMN_ORDER_IDBOOK+" INTEGER,"+COLUMN_ORDER_NAME+" TEXT, "+COLUMN_ORDER_PRICE+" INTEGER, "+COLUMN_ORDER_IMAGE+" blob, FOREIGN KEY ("+COLUMN_ORDER_UESRNAME +") REFERENCES "+ USERSTABLE +"("+USERNAMECOL+") , FOREIGN KEY ("+COLUMN_ORDER_IDBOOK +") REFERENCES "+ BOOK_TABLE +"("+BOOK_ID+"), FOREIGN KEY ("+COLUMN_ORDER_NAME +") REFERENCES "+ BOOK_TABLE +"("+COLUMN_BOOK_NAME+"), FOREIGN KEY ("+COLUMN_ORDER_PRICE +") REFERENCES "+ BOOK_TABLE +"("+COLUMN_BOOK_PRICE+"),FOREIGN KEY ("+COLUMN_ORDER_IMAGE +") REFERENCES "+ BOOK_TABLE +"(image)"+")");
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
        cv.put(COLUMN_BOOK_AUTHOR,book.getAuthor());
        cv.put(COLUMN_BOOK_USERNAME,UserInfo.username);



        long insert = db.insert(BOOK_TABLE, null, cv);
        if (insert== -1){return false;}
        else {return true;}

    }

    /////DELETE
    public List<BookModel> getBook(){
        List<BookModel> returnList = new ArrayList<>();
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
                String BookAuthor=cursor.getString(5);
                @SuppressLint("Range") String username =cursor.getString(cursor.getColumnIndex(COLUMN_BOOK_USERNAME));


                BookModel newCustomer = new BookModel(bookID, BookName, BookPrice, BookState,image,BookAuthor,username);
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

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + BOOK_TABLE + " where BookID=" +id , null);
        BookModel bookModel=null;

        if (cursor.moveToFirst()) {



                @SuppressLint("Range") int bookid = cursor.getInt(cursor.getColumnIndex(BOOK_ID));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(COLUMN_BOOK_NAME));
                @SuppressLint("Range") int price = cursor.getInt(cursor.getColumnIndex(COLUMN_BOOK_PRICE));
                 @SuppressLint("Range") String status =cursor.getString(cursor.getColumnIndex(COLUMN_BOOK_STATE));
            @SuppressLint("Range") String Author =cursor.getString(cursor.getColumnIndex(COLUMN_BOOK_AUTHOR));
                @SuppressLint("Range") byte[] image = cursor.getBlob(cursor.getColumnIndex("image"));



                bookModel = new BookModel(bookid,name,price,status,image,Author,UserInfo.username);
           }

         return bookModel;
        }



        public Boolean Renting(BookModel book){
            SQLiteDatabase db= this.getWritableDatabase();
            Cursor cursor = db.rawQuery("Select * from " + ORDERSTABLE + " where " + COLUMN_ORDER_IDBOOK + " ="+book.getId(),null );
           if(cursor.getCount() == 0){
               ContentValues cv = new ContentValues();
               cv.put(COLUMN_ORDER_UESRNAME,book.getUserName());
               cv.put(COLUMN_ORDER_IDBOOK,book.getId());
               cv.put(COLUMN_ORDER_NAME,book.getName());
               cv.put(COLUMN_ORDER_PRICE,book.getPrice());
               cv.put(COLUMN_ORDER_IMAGE,book.getImage());
               db.insert(ORDERSTABLE, null, cv);

               return true;
           }
           else{
            return false;
           }

        }


        public ArrayList<RentedBookModel> MyRentals(String username){
            ArrayList<RentedBookModel> rentedBooks = new ArrayList<>();
            RentedBookModel book=null;
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM " + ORDERSTABLE + " where "+COLUMN_ORDER_UESRNAME +"='"+username+"'", null);
            if(cursor.moveToFirst()){
                do {
                    @SuppressLint("Range") int bookid = cursor.getInt(cursor.getColumnIndex(COLUMN_ORDER_IDBOOK));
                    @SuppressLint("Range") String username1 = cursor.getString(cursor.getColumnIndex(COLUMN_ORDER_UESRNAME));
                    @SuppressLint("Range") int Rentid = cursor.getInt(cursor.getColumnIndex(COLUMN_ORDER_ID));
                    @SuppressLint("Range") String Bname= cursor.getString(cursor.getColumnIndex(COLUMN_ORDER_NAME));
                    @SuppressLint("Range") int Bprice = cursor.getInt(cursor.getColumnIndex(COLUMN_ORDER_PRICE));
                    @SuppressLint("Range") byte[] image = cursor.getBlob(cursor.getColumnIndex(COLUMN_ORDER_IMAGE));




                    book = new RentedBookModel(Rentid,username1,Bname,bookid,Bprice,image);
                    rentedBooks.add(book);

                }while(cursor.moveToNext());

            }

            return rentedBooks;
        }


    public boolean Return(RentedBookModel Rentedbook){

        SQLiteDatabase db = this.getWritableDatabase();
        String queryString= "Delete From " + ORDERSTABLE + " Where "+COLUMN_ORDER_ID+" ="+Rentedbook.getRentID();
        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()){
            return true;
        } else{

            return false;
        }

    }

    }




