package com.example.bookshelf;

import android.graphics.Bitmap;

public class BookModel {

    private int id=0;
    private String name;
    private int price;
    private String state;

    private byte[] image;

    private String author;

    private String UserName;


    public BookModel(int id, String name, int price, String state,byte[] image,String author, String UserName) {
        this.id = id++;
        this.name = name;
        this.price = price;
        this.state = state;
        this.image=image;
        this.author=author;
        this.UserName=UserName;

    }


    public BookModel() {
    }



    @Override
    public String toString() {
        return "BookModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", state='" + state + '\'' +
                '}';
    }

//getters

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getState() {
        return state;
    }
    //setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
    public byte[] getImage() {
        return image;
    }
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }
}
