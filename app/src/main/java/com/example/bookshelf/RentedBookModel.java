package com.example.bookshelf;

public class RentedBookModel {
    private int rentID;
    private String username,Bname;
    private int BookID,price;
    private byte[] image;

    public RentedBookModel(int rentID, String username, String bname, int bookID, int price, byte[] image) {
        this.rentID = rentID;
        this.username = username;
        Bname = bname;
        BookID = bookID;
        this.price = price;
        this.image = image;
    }

    public int getRentID() {
        return rentID;
    }

    public void setRentID(int rentID) {
        this.rentID = rentID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getBookID() {
        return BookID;
    }

    public void setBookID(int bookID) {
        BookID = bookID;
    }

    public String getBname() {
        return Bname;
    }

    public void setBname(String bname) {
        Bname = bname;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
